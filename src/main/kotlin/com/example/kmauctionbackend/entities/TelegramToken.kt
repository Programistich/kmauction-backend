package com.example.kmauctionbackend.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "telegram_tokens")
data class TelegramToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "token", nullable = false)
    var token: String,

    @CreationTimestamp
    @Column(name = "created_time", nullable = false)
    var сreatedTime: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    var user: User? = null
) {
    fun isValid(): Boolean {
        return сreatedTime >= LocalDateTime.now().minusMinutes(10)
    }
}
