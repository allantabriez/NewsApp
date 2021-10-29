package com.example.newsapp.data.remote.body

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    @SerialName("username")
    val userName: String,

    @SerialName("password")
    val pass: String,
)
