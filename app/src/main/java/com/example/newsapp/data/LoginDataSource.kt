package com.example.newsapp.data

import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource

interface LoginDataSource {
    suspend fun doLogin(username: String, pass: String): Resource<TokenResponse>
    suspend fun refreshToken(): Resource<TokenResponse>
    fun saveSession(token: String, expiredAt: String)
    fun getToken(): String?
    fun getExpiredAt(): String?
    fun deleteSession()
}