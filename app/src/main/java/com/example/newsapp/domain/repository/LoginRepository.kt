package com.example.newsapp.domain.repository

import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun doLogin(username: String, pass: String): Flow<Resource<Any>>
    suspend fun refreshToken(): Flow<Resource<Any>>
}