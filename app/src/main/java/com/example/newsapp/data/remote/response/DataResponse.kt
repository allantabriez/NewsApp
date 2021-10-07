package com.example.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(

    @field:SerializedName("data")
    val data: T? = null
)