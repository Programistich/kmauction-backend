package com.example.kmauctionbackend.config

import com.example.kmauctionbackend.security.SecurityExceptionEntryPoint
import com.example.kmauctionbackend.security.azure.AzureAuthFilter
import com.example.kmauctionbackend.security.azure.AzureAuthProvider
import com.example.kmauctionbackend.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: UserService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .addFilterBefore(AzureAuthFilter(authenticationManager()), LogoutFilter::class.java)
            .exceptionHandling { it.authenticationEntryPoint(customEntryPoint()) }
            .build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(AzureAuthProvider(userService))
    }

    @Bean
    fun customEntryPoint(): SecurityExceptionEntryPoint {
        return SecurityExceptionEntryPoint()
    }
}

