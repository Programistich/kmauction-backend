package com.example.kmauctionbackend.security.azure

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class AzureAuthFilter(private val authenticationManager: AuthenticationManager) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getBearerToken(request)

        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }

        runCatching {
            val azureAuth = AzureAuth(token)
            val authentication = authenticationManager.authenticate(azureAuth)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getBearerToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")
        return header?.replace("Bearer ", "")
    }
}
