package com.example.kmauctionbackend.service

import com.example.kmauctionbackend.entities.User
import com.example.kmauctionbackend.exceptions.BusinessLogicException
import com.example.kmauctionbackend.exceptions.UnauthorizedException
import com.example.kmauctionbackend.repository.UserRepository
import com.example.kmauctionbackend.security.AuthUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class IdentityService(
    private val userRepository: UserRepository
) {
    fun getCurrentUserEmail(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val authUser = principal as? AuthUser ?: throw UnauthorizedException("User not authenticated")
        return authUser.email
    }

    fun getCurrentUser(): User {
        val email = getCurrentUserEmail()
        return userRepository.findByEmail(email) ?: throw BusinessLogicException("User not found")
    }
}
