package com.example.newsapp.domain.usecase

import com.example.newsapp.utils.Resource

interface RefreshTokenUseCase {
    suspend fun refreshToken(): Resource<Any>
}