package com.example.kmauctionbackend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("telegram", ignoreInvalidFields = false)
data class TelegramConfig @ConstructorBinding constructor(
    val botToken: String,
    val botUsername: String,
)
