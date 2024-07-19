package com.example.kmauctionbackend.repository

import com.example.kmauctionbackend.entities.TelegramToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TelegramTokenRepository : JpaRepository<TelegramToken, Long> {
    fun findByUserId(userId: Long): List<TelegramToken>
    fun findByToken(token: String): TelegramToken?
}
