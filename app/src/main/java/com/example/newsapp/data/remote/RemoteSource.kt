package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
    suspend fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>>
    suspend fun refresh(): Flow<Resource<TokenResponse>>
    suspend fun getProfile(): Flow<Resource<ProfileResponse>>
    suspend fun getNews(): Flow<Resource<List<NewsResponse>>>
}