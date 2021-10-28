package com.example.newsapp.data

import com.example.newsapp.domain.model.Token
import com.example.newsapp.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val localSource: LoginDataSource,
    private val remoteSource: LoginDataSource
) : LoginRepository {
    override suspend fun doLogin(username: String, pass: String): Token {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): Token {
        TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}