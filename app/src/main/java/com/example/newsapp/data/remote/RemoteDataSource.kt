package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>>
    fun refreshToken(): Flow<Resource<TokenResponse>>
    fun getNews(): Flow<Resource<List<NewsResponse>>>
    fun getProfile(): Flow<Resource<ProfileResponse>>
}