package com.example.newsapp.domain.model

data class Token(
    val expiresAt: String,
    val scheme: String,
    val token: String
)
