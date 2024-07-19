package com.example.kmauctionbackend.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val id: Long,
    val email: String,
    val name: String,
    @SerialName("is_telegram_connected")
    val isTelegramConnected: Boolean
)
