package com.example.newsapp.data.remote

import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRemoteDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
) : LoginDataSource {
    override fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>> {
        return flow {
            val response = loginService.doLogin(username, pass)
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(code = ErrorCode.fromInt(response.code())))
        }.flowOn(ioDispatcher)
    }

    override fun refreshToken(): Flow<Resource<TokenResponse>> {
        return flow {
            val response = loginService.refreshToken()
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(code = ErrorCode.fromInt(response.code())))
        }.flowOn(ioDispatcher)
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