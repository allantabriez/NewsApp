package com.example.newsapp.data.remote.network

import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.data.remote.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun doLogin(
        @Field("username") userName: String,
        @Field("password") password: String,
    ): Response<TokenResponse>

    @GET("auth/token")
    suspend fun refreshToken(): Response<TokenResponse>

    @GET("me/profile")
    suspend fun getProfile(): Response<ProfileResponse>

    @GET("me/news")
    suspend fun getNews(): Response<DataResponse<List<NewsResponse>>>
}