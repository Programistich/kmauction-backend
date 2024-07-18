package com.example.kmauctionbackend.security

import org.springframework.security.core.userdetails.UserDetails

data class AuthUser(
    val email: String,
    val name: String
) : UserDetails {
    override fun getAuthorities() = null
    override fun getPassword() = null
    override fun getUsername() = name
}
