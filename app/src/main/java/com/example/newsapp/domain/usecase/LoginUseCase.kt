package com.example.newsapp.domain.usecase

import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun doLogin(username: String, pass: String): Flow<Resource<Any>>
}