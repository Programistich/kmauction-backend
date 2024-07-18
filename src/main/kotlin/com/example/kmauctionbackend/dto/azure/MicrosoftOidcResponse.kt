package com.example.kmauctionbackend.dto.azure

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MicrosoftOidcResponse(
    @SerialName("family_name")
    val familyName: String,
    @SerialName("given_name")
    val givenName: String,
    val email: String
) {
    val name: String
        get() = "$givenName $familyName"
}
