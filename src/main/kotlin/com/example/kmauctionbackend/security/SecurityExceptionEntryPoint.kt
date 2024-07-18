package com.example.kmauctionbackend.security

import com.example.kmauctionbackend.exceptions.UnauthorizedException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class SecurityExceptionEntryPoint : AuthenticationEntryPoint {
    @Autowired
    private lateinit var handlerExceptionResolver: HandlerExceptionResolver

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        if (authException is InsufficientAuthenticationException) {
            handlerExceptionResolver.resolveException(
                request,
                response,
                null,
                UnauthorizedException("Unauthorized")
            )
            return
        }
        handlerExceptionResolver.resolveException(request, response, null, authException)
    }
}
