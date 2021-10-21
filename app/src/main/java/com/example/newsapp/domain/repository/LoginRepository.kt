package com.example.newsapp.domain.repository

import com.example.newsapp.utils.Resource

interface LoginRepository {
    suspend fun doLogin(username: String, pass: String): Resource<Any>
    suspend fun refreshToken(): Resource<Any>
    fun isLoggedIn(): Boolean
}