package com.example.kmauctionbackend.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerErrorResponse(
    val message: String?,
    val cause: String
)
