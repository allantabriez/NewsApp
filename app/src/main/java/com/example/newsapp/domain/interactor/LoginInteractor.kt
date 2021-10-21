package com.example.newsapp.domain.interactor

import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.IsLoggedInUseCase
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.domain.usecase.RefreshTokenUseCase

class LoginInteractor(private val repository: LoginRepository) : LoginUseCase, RefreshTokenUseCase,
    IsLoggedInUseCase {
    override suspend fun doLogin(username: String, pass: String) =
        repository.doLogin(username, pass)

    override suspend fun refreshToken() = repository.refreshToken()
    override suspend fun isLoggedIn(): Boolean = repository.isLoggedIn()
}