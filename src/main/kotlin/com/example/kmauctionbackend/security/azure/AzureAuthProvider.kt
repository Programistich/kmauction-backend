package com.example.kmauctionbackend.security.azure

import com.example.kmauctionbackend.dto.azure.MicrosoftOidcResponse
import com.example.kmauctionbackend.exceptions.UnauthorizedException
import com.example.kmauctionbackend.security.AuthUser
import com.example.kmauctionbackend.service.UserService
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class AzureAuthProvider(
    private val userService: UserService
) : AuthenticationProvider {
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    override fun authenticate(authentication: Authentication): Authentication {
        if (authentication !is AzureAuth) return authentication

        val authUser =
            getMicrosoftOidc(authentication.token)
                .getOrElse { throw BadCredentialsException("Invalid token from Microsoft.") }
                .also { saveUser(it) }
                .let { AuthUser(email = it.email, name = it.name) }

        return AzureAuth(token = authentication.token, authUser = authUser)
    }

    private fun saveUser(oidc: MicrosoftOidcResponse) {
        if (userService.getUser(oidc.email) == null) {
            userService.createUser(oidc.email, oidc.name)
        }
    }

    // TODO think about use azure sdk
    private fun getMicrosoftOidc(token: String): Result<MicrosoftOidcResponse> {
        val client = HttpClient.newHttpClient()

        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI("https://graph.microsoft.com/oidc/userinfo"))
            .GET()
            .header("Authorization", "Bearer $token")
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() != HttpStatus.OK.value()) {
            return Result.failure(UnauthorizedException("Invalid token from Microsoft."))
        }

        val microsoftOidc = json.decodeFromString<MicrosoftOidcResponse>(response.body())
        return Result.success(microsoftOidc)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == AzureAuth::class.java
    }
}
