package com.example.newsapp.data.remote.network

import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Constants.LOGIN_URL
import com.example.newsapp.utils.Constants.REFRESH_URL
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {

    @POST(LOGIN_URL)
    suspend fun doLogin(
        @Field("username") userName: String,
        @Field("password") password: String,
    ): Response<TokenResponse>

    @GET(REFRESH_URL)
    suspend fun refreshToken(): Response<TokenResponse>
}