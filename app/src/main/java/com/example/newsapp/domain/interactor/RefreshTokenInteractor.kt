package com.example.newsapp.domain.interactor

import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.RefreshTokenUseCase
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class RefreshTokenInteractor(private val repository: LoginRepository): RefreshTokenUseCase {
    override suspend fun refreshToken() = repository.refreshToken()
}