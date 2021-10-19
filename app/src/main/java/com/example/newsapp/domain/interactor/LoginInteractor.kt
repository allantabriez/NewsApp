package com.example.newsapp.domain.interactor

import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class LoginInteractor(private val repository: LoginRepository) : LoginUseCase {
    override suspend fun doLogin(username: String, pass: String) = repository.doLogin(username, pass)
}