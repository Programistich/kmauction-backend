package com.example.kmauctionbackend.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Long,
    val email: String,
    val name: String
)
