package com.example.newsapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    val data: T? = null
)