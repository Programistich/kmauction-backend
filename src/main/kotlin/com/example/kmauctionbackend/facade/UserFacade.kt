package com.example.kmauctionbackend.facade

import com.example.kmauctionbackend.dto.user.ConnectedTelegramDto
import com.example.kmauctionbackend.dto.user.UserInfoDto
import com.example.kmauctionbackend.service.IdentityService
import com.example.kmauctionbackend.service.TelegramService
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val identityService: IdentityService,
    private val telegramService: TelegramService
) {
    fun getMe(): UserInfoDto {
        val user = identityService.getCurrentUser()
        return UserInfoDto(
            id = user.id,
            email = user.email,
            name = user.name,
            isTelegramConnected = user.telegramId != null
        )
    }

    fun connectTelegram(): ConnectedTelegramDto {
        val user = identityService.getCurrentUser()
        val token = telegramService.getToken(user.id)
        val url = telegramService.generateUrl(token)

        return ConnectedTelegramDto(telegramAuthUrl = url)
    }
}
