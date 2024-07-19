package com.example.kmauctionbackend.cronjob

import com.example.kmauctionbackend.repository.TelegramTokenRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TelegramTokenCronJob(
    private val telegramTokenRepository: TelegramTokenRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "0 0 0 * * *") // Every day at midnight
    fun deleteOldTokens() {
        logger.info("Run cronjob with deleting old tokens")
        telegramTokenRepository
            .findAll()
            .filter { it.isValid().not() }
            .forEach { telegramTokenRepository.delete(it) }
    }
}
