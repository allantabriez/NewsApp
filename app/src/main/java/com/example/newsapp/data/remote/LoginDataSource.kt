package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.DataMapper
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginDataSource(
    private val loginService: LoginService,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>> {
        return flow {
            emit(Resource.Loading())
            val response = loginService.doLogin(username, pass)
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }

    fun refresh(): Flow<Resource<TokenResponse>> {
        return flow {
            emit(Resource.Loading())
            val response = loginService.refreshToken()
            if (response.isSuccessful) emit(Resource.Success(response.body() as TokenResponse))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }
}