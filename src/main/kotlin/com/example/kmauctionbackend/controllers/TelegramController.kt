package com.example.kmauctionbackend.controllers

import com.example.kmauctionbackend.config.TelegramConfig
import com.example.kmauctionbackend.dto.telegram.TelegramTokenConnect
import com.example.kmauctionbackend.facade.TelegramFacade
import com.example.kmauctionbackend.service.TelegramSender
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramController(
    private val telegramConfig: TelegramConfig,
    private val telegramFacade: TelegramFacade,
    private val telegramSender: TelegramSender
) : SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    override fun getBotToken(): String = telegramConfig.botToken
    override fun getUpdatesConsumer() = this

    override fun consume(update: Update) {
        val chatId = update.message?.chatId ?: return
        val result = telegramFacade.consume(update)

        val text = when (result) {
            TelegramTokenConnect.Expired -> "Даний токен не дійсний, спробуй ще раз"
            TelegramTokenConnect.NotFound -> "Даний токен не знайдено"
            TelegramTokenConnect.NotRecognized -> "Даний токен не розпізнано"
            TelegramTokenConnect.Success -> "Telegram успішно підключено"
        }

        telegramSender.sendText(text, chatId.toString())
    }
}
