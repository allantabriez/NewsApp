package com.example.newsapp.data

import com.example.newsapp.domain.model.Token
import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.utils.DateUtils

class LoginRepositoryImpl(
    private val localSource: LoginDataSource,
    private val remoteSource: LoginDataSource,
    private val dateUtils: DateUtils
) : LoginRepository {
    override suspend fun doLogin(username: String, pass: String): Token {
        val result = remoteSource.doLogin(username, pass)
        localSource.saveSession(result.token, result.expiresAt)
        return result.toModel()
    }

    override suspend fun refreshToken(): Token {
        val expiredDate = localSource.getExpiredAt()
        return try {
            if (dateUtils.between1HourAndNow(expiredDate)) {
                val result = remoteSource.refreshToken()
                localSource.saveSession(result.token, result.expiresAt)
                result.toModel()
            } else {
                Token(
                    expiredDate ?: "",
                    localSource.getToken() ?: ""
                )
            }
        } catch (e: Throwable) {
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
        if (dateUtils.isDateAfterCurrentTime(expiredDate)) return false

        return true
    }
}