package com.example.kmauctionbackend.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnectedTelegramDto(
    @SerialName("telegram_auth_url")
    val telegramAuthUrl: String
)
