package com.example.newsapp.domain.usecase

import com.example.newsapp.utils.Resource

interface LoginUseCase {
    suspend fun doLogin(username: String, pass: String): Resource<Any>
}