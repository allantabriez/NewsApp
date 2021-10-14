package com.example.newsapp.data.remote

import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Constants.FUNCTION_NOT_USED
import com.example.newsapp.utils.DataMapper
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRemoteDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
): LoginDataSource {
    override fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>> {
        return flow {
            emit(Resource.Loading())
            val response = loginService.doLogin(username, pass)
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }

    override fun refreshToken(): Flow<Resource<TokenResponse>> {
        return flow {
            emit(Resource.Loading())
            val response = loginService.refreshToken()
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }

    override fun saveSession(token: String, expiredAt: String) {
        throw Exception(FUNCTION_NOT_USED)
    }

    override fun getToken(): String {
        throw Exception(FUNCTION_NOT_USED)
    }

    override fun getExpiredAt(): String {
        throw Exception(FUNCTION_NOT_USED)
    }

    override fun deleteSession() {
        throw Exception(FUNCTION_NOT_USED)
    }
}