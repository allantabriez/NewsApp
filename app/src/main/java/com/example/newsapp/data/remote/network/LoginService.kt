package com.example.newsapp.data.remote.network

import com.example.newsapp.data.remote.body.LoginBody
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Constants.LOGIN_URL
import com.example.newsapp.utils.Constants.REFRESH_URL
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @POST(LOGIN_URL)
    suspend fun doLogin(
        @Body body: LoginBody
    ): Response<TokenResponse>

    @GET(REFRESH_URL)
    suspend fun refreshToken(): Response<TokenResponse>
}