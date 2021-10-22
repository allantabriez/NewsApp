package com.example.newsapp.domain.repository

import com.example.newsapp.utils.Resource

interface LoginRepository {
    suspend fun doLogin(username: String, pass: String): Resource<Unit>
    suspend fun refreshToken(): Resource<Unit>
    fun isLoggedIn(): Boolean
}