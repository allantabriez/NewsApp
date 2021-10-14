package com.example.newsapp.data

import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>>
    fun refreshToken(): Flow<Resource<TokenResponse>>
    fun saveSession(token: String, expiredAt: String)
    fun getToken(): String
    fun getExpiredAt(): String
    fun deleteSession()
}