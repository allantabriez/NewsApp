package com.example.newsapp.domain.usecase

interface IsLoggedInUseCase {
    suspend fun isLoggedIn(): Boolean
}