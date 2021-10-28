package com.example.newsapp.data

import com.example.newsapp.data.remote.response.toModel
import com.example.newsapp.domain.model.Token
import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.utils.DateUtils

class LoginRepositoryImpl(
    private val localSource: LoginDataSource,
    private val remoteSource: LoginDataSource
) : LoginRepository {
    override suspend fun doLogin(username: String, pass: String): Token {
        val token = remoteSource.doLogin(username, pass)
        localSource.saveSession(token.token, token.expiresAt)
        return token.toModel()
    }

    override suspend fun refreshToken(): Token {
        val expiredDate = localSource.getExpiredAt()
        return if (DateUtils.between1HourAndNow(expiredDate)) {
            remoteSource.refreshToken().toModel()
        } else {
            Token(
                expiredDate ?: "",
                localSource.getToken() ?: ""
            )
        }
    }

    override fun isLoggedIn(): Boolean {
        val currentToken = localSource.getToken()
        val expiredDate = localSource.getExpiredAt()

        if (currentToken.isNullOrBlank()) return false
        if (expiredDate.isNullOrBlank()) return false
        if (DateUtils.isDateAfterCurrentTime(expiredDate)) return false

        return true
    }
}