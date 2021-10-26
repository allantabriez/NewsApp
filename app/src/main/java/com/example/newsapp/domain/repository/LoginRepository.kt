package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.Token

interface LoginRepository {
    suspend fun doLogin(username: String, pass: String): Token
    suspend fun refreshToken(): Token
    fun isLoggedIn(): Boolean
}