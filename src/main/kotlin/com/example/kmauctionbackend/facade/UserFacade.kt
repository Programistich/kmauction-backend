package com.example.kmauctionbackend.facade

import com.example.kmauctionbackend.dto.user.UserDto
import com.example.kmauctionbackend.service.IdentityService
import com.example.kmauctionbackend.service.UserService
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userService: UserService,
    private val identityService: IdentityService
) {
    fun getMe(): UserDto {
        val email = identityService.getCurrentUserEmail()
        val user = userService.getUser(email = email) ?: throw RuntimeException("User not found")

        return UserDto(
            id = user.id,
            email = user.email,
            name = user.name
        )
    }
}
