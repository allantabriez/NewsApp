package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(username: String, pass: String) = repository.doLogin(username, pass)
}