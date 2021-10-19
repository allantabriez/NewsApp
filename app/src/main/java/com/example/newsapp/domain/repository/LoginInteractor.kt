package com.example.newsapp.domain.repository

import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.domain.usecase.RefreshTokenUseCase

class LoginInteractor(private val repository: LoginRepository): LoginUseCase, RefreshTokenUseCase {
    override suspend fun doLogin(username: String, pass: String) = repository.doLogin(username, pass)

    override suspend fun refreshToken() = repository.refreshToken()
}