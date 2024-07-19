package com.example.kmauctionbackend

import com.example.kmauctionbackend.config.TelegramConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableConfigurationProperties(TelegramConfig::class)
@EnableScheduling
class KmauctionBackendApplication

fun main(args: Array<String>) {
    runApplication<KmauctionBackendApplication>(*args)
}
