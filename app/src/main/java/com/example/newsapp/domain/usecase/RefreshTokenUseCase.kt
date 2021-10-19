package com.example.newsapp.domain.usecase

import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RefreshTokenUseCase {
    suspend fun refreshToken(): Flow<Resource<Any>>
}