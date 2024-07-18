package com.example.kmauctionbackend.service

import com.example.kmauctionbackend.entities.User
import com.example.kmauctionbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUser(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun createUser(email: String, name: String): User {
        val user = User(email = email, name = name)
        return userRepository.save(user)
    }
}
