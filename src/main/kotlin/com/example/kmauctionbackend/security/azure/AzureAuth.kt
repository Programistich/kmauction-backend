package com.example.kmauctionbackend.security.azure

import com.example.kmauctionbackend.security.AuthUser
import org.springframework.security.core.Authentication

data class AzureAuth(val token: String) : Authentication {
    private var isAuthenticated: Boolean = false
    private var authUser: AuthUser? = null

    constructor(token: String, authUser: AuthUser) : this(token) {
        this.isAuthenticated = true
        this.authUser = authUser
    }

    override fun getName() = authUser?.email

    override fun getAuthorities() = authUser?.authorities

    override fun getCredentials(): Any = token

    override fun getDetails() = authUser

    override fun getPrincipal() = authUser

    override fun isAuthenticated(): Boolean = isAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) = Unit
}
