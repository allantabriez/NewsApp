package com.example.newsapp.data.remote.network

import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun doLogin(
        @Field("username") userName: String,
        @Field("password") password: String,
    )

    @GET("auth/token")
    suspend fun refreshToken()

    @GET("me/profile")
    suspend fun getProfile()

    @GET("me/news")
    suspend fun getNews()
}