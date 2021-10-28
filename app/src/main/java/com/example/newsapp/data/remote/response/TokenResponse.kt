package com.example.newsapp.data.remote.response

import com.example.newsapp.domain.model.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(

    @SerialName("expires_at")
    val expiresAt: String,

    @SerialName("scheme")
    val scheme: String,

    @SerialName("token")
    val token: String
)

fun TokenResponse.toModel() = Token(
    this.expiresAt, this.token
)
