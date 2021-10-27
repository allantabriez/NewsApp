package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.LoginRepository

class RefreshTokenUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke() = repository.refreshToken()
}