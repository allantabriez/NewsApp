package com.example.newsapp.data

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MeDataSource {
    suspend fun getNews(): Resource<List<NewsResponse>>
    suspend fun getProfile(): Resource<ProfileResponse>
    fun getCachedNews(): Flow<List<NewsEntity>>
    fun getCachedProfile(): Flow<List<ProfileEntity>>
    suspend fun insertNews(news: List<NewsEntity>)
    suspend fun insertProfile(profile: ProfileEntity)
    suspend fun deleteCachedNews()
    suspend fun deleteCachedProfile()
}