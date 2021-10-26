package com.example.newsapp.data

import com.example.newsapp.data.remote.response.TokenResponse

interface LoginDataSource {
    suspend fun doLogin(username: String, pass: String): TokenResponse
    suspend fun refreshToken(): TokenResponse
    fun saveSession(token: String, expiredAt: String)
    fun getToken(): String?
    fun getExpiredAt(): String?
    fun deleteSession()
}