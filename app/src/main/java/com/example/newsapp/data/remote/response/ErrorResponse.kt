package com.example.newsapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String? = null
)
