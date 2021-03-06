package com.example.newsapp.data.remote

import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.body.LoginBody
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable
import com.example.newsapp.utils.UnusedFunctionThrowable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginRemoteDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
) : LoginDataSource {
    override suspend fun doLogin(username: String, pass: String): TokenResponse =
        withContext(ioDispatcher) {
            try {
                val response = loginService.doLogin(LoginBody(username, pass))
                if (response.isSuccessful) return@withContext response.body() as TokenResponse
                else {
                    throw NetworkThrowable(response.message(), ErrorCode.fromInt(response.code()))
                }
            } catch (e: Exception) {
                throw NetworkThrowable(e.message, ErrorCode.CodeUnknown)
            }
        }

    override suspend fun refreshToken(): TokenResponse = withContext(ioDispatcher) {
        try {
            val response = loginService.refreshToken()
            if (response.isSuccessful) return@withContext response.body() as TokenResponse
            else throw NetworkThrowable(response.message(), ErrorCode.fromInt(response.code()))
        } catch (e: Exception) {
            throw NetworkThrowable(e.message, ErrorCode.CodeUnknown)
        }
    }

    override fun saveSession(token: String, expiredAt: String) {
        throw UnusedFunctionThrowable()
    }

    override fun getToken(): String {
        throw UnusedFunctionThrowable()
    }

    override fun getExpiredAt(): String {
        throw UnusedFunctionThrowable()
    }

    override fun deleteSession() {
        throw UnusedFunctionThrowable()
    }
}