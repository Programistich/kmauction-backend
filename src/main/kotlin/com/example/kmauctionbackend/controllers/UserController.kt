package com.example.kmauctionbackend.controllers

import com.example.kmauctionbackend.dto.user.ConnectedTelegramDto
import com.example.kmauctionbackend.dto.user.UserInfoDto
import com.example.kmauctionbackend.facade.UserFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userFacade: UserFacade) {

    @GetMapping("/me")
    fun getMe(): UserInfoDto {
        return userFacade.getMe()
    }

    @GetMapping("/connect-telegram")
    fun connectTelegram(): ConnectedTelegramDto {
        return userFacade.connectTelegram()
    }
}
