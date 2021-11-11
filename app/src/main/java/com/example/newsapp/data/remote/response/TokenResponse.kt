package com.example.newsapp.data.remote.response

import com.example.newsapp.domain.model.Token
import com.example.newsapp.utils.UnusedFunctionThrowable
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
) : ResponseMapper<Token, Unit> {
    override fun toModel(): Token {
        return Token(
            this.expiresAt, this.token
        )
    }

    override fun toEntity() {
        throw UnusedFunctionThrowable()
    }

}
