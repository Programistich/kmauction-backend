package com.example.kmauctionbackend.dto.telegram

sealed class TelegramTokenConnect {
    data object NotRecognized : TelegramTokenConnect()
    data object NotFound : TelegramTokenConnect()
    data object Expired : TelegramTokenConnect()
    data object Success : TelegramTokenConnect()
}