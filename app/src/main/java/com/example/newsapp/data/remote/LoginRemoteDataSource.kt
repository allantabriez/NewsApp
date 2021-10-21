package com.example.newsapp.data.remote

import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRemoteDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
) : LoginDataSource {
    override suspend fun doLogin(username: String, pass: String): Resource<TokenResponse> =
        withContext(ioDispatcher) {
            val response = loginService.doLogin(username, pass)
            if (response.isSuccessful) return@withContext Resource.Success(response.body() as TokenResponse)
            else return@withContext Resource.Error(ErrorCode.fromInt(response.code()))
        }

    override suspend fun refreshToken(): Resource<TokenResponse> = withContext(Dispatchers.IO) {
        val response = loginService.refreshToken()
        if (response.isSuccessful) return@withContext Resource.Success(response.body() as TokenResponse)
        else return@withContext Resource.Error(ErrorCode.fromInt(response.code()))
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