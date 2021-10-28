package com.example.newsapp.data.remote

import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkException
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class LoginRemoteDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
) : LoginDataSource {
    override suspend fun doLogin(username: String, pass: String): TokenResponse =
        withContext(ioDispatcher) {
            try {
                val response = loginService.doLogin(username, pass)
                if (response.isSuccessful) return@withContext response.body() as TokenResponse
                else {
                    throw NetworkException(response.message(), ErrorCode.fromInt(response.code()))
                }
            } catch (e: Exception) {
                throw NetworkException(e.message, ErrorCode.CodeUnknown)
            }
        }

    override suspend fun refreshToken(): TokenResponse = withContext(Dispatchers.IO) {
        try {
            val response = loginService.refreshToken()
            if (response.isSuccessful) return@withContext response.body() as TokenResponse
            else throw NetworkException(response.message(), ErrorCode.fromInt(response.code()))
        } catch (e: Exception) {
            throw NetworkException(e.message, ErrorCode.CodeUnknown)
        }
    }

    override fun saveSession(token: String, expiredAt: String) {
        throw UnusedFunctionException()
    }

    override fun getToken(): String {
        throw UnusedFunctionException()
    }

    override fun getExpiredAt(): String {
        throw UnusedFunctionException()
    }

    override fun deleteSession() {
        throw UnusedFunctionException()
    }
}