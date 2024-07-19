package com.example.kmauctionbackend.facade

import com.example.kmauctionbackend.config.TelegramConfig
import com.example.kmauctionbackend.dto.telegram.TelegramTokenConnect
import com.example.kmauctionbackend.service.TelegramService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.EntityType
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class TelegramFacade(
    private val telegramConfig: TelegramConfig,
    private val telegramService: TelegramService
) {

    fun consume(update: Update): TelegramTokenConnect {
        val message = update.message ?: return TelegramTokenConnect.NotRecognized
        val userId = message.from?.id ?: return TelegramTokenConnect.NotRecognized

        val token = message.getStartToken() ?: return TelegramTokenConnect.NotRecognized
        val telegramToken = telegramService.getTelegramToken(token) ?: return TelegramTokenConnect.NotFound

        return if (telegramToken.isValid()) {
            telegramService.saveTelegramId(telegramToken, userId)
            TelegramTokenConnect.Success
        } else {
            TelegramTokenConnect.Expired
        }
    }


    private fun Message.getStartToken(botName: String = telegramConfig.botUsername): String? {
        val text = this.text ?: return null
        val entities = this.entities ?: return null

        val commandEntity = entities.firstOrNull { it.type == EntityType.BOTCOMMAND } ?: return null
        val commandText = text
            .substringOrNull(
                startIndex = commandEntity.offset,
                endIndex = commandEntity.offset + commandEntity.length
            ) ?: return null

        val (command, bot) = commandText.splitCommand("@")

        return when {
            command != "/start" -> null
            bot.isNullOrEmpty() -> text.replace(command, "").trim()
            bot == botName -> text.replace("$command@$botName", "").trim()
            else -> null
        }
    }

    private fun String.substringOrNull(startIndex: Int, endIndex: Int): String? {
        return if (startIndex in 0..length && endIndex in 0..length) substring(startIndex, endIndex) else null
    }

    private fun String.splitCommand(delimiter: String): Pair<String, String?> {
        val parts = split(delimiter)
        return if (parts.size == 1) parts[0] to null else parts[0] to parts[1]
    }
}
