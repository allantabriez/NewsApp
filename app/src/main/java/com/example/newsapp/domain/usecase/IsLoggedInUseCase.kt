package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.LoginRepository

class IsLoggedInUseCase(private val repository: LoginRepository) {
    operator fun invoke() = repository.isLoggedIn()
}