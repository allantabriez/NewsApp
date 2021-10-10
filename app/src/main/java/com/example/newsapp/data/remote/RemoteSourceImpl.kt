package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class RemoteSourceImpl: RemoteSource {
    override suspend fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(): Flow<Resource<TokenResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Flow<Resource<ProfileResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNews(): Flow<Resource<List<NewsResponse>>> {
        TODO("Not yet implemented")
    }
}