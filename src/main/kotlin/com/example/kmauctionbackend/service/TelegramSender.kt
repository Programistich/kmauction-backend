package com.example.kmauctionbackend.service

import com.example.kmauctionbackend.config.TelegramConfig
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Component
class TelegramSender(telegramConfig: TelegramConfig) {
    private val telegramClient = OkHttpTelegramClient(telegramConfig.botToken)

    fun sendText(text: String, chatId: String) {
        val sendMessage = SendMessage(chatId, text)
        telegramClient.execute(sendMessage)
    }
}
