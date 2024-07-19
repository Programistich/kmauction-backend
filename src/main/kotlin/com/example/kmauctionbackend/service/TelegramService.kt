package com.example.kmauctionbackend.service

import com.example.kmauctionbackend.config.TelegramConfig
import com.example.kmauctionbackend.entities.TelegramToken
import com.example.kmauctionbackend.repository.TelegramTokenRepository
import com.example.kmauctionbackend.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class TelegramService(
    private val telegramConfig: TelegramConfig,
    private val telegramTokenRepository: TelegramTokenRepository,
    private val userRepository: UserRepository
) {
    fun getToken(userId: Long): String {
        return telegramTokenRepository.findByUserId(userId)
            .firstOrNull { it.isValid() }
            .let { it ?: processNewToken(userId) }.token
    }

    fun generateUrl(token: String): String {
        return "https://t.me/${telegramConfig.botUsername}?start=$token"
    }

    private fun processNewToken(userId: Long): TelegramToken {
        val oldTokens = telegramTokenRepository.findByUserId(userId)
        oldTokens.forEach { telegramTokenRepository.delete(it) }

        val token = generateRandomToken()

        val telegramToken = TelegramToken(
            userId = userId,
            token = token,
            сreatedTime = LocalDateTime.now()
        )
        return telegramTokenRepository.save(telegramToken)
    }

    private fun generateRandomToken(): String {
        return (1..10).map { ('a'..'z').random() }.joinToString("")
    }

    fun getTelegramToken(token: String) = telegramTokenRepository.findByToken(token)

    fun saveTelegramId(telegramToken: TelegramToken, telegramId: Long) {
        val user = userRepository.findById(telegramToken.userId).getOrNull() ?: return
        userRepository.save(user.copy(telegramId = telegramId))
        telegramTokenRepository.delete(telegramToken)
    }
}
