package com.example.kmauctionbackend.service

import com.example.kmauctionbackend.exceptions.UnauthorizedException
import com.example.kmauctionbackend.security.AuthUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class IdentityService {
    fun getCurrentUserEmail(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val authUser = principal as? AuthUser ?: throw UnauthorizedException("User not authenticated")
        return authUser.email
    }
}
