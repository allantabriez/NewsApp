package com.example.newsapp.data

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse

interface MeDataSource {
    suspend fun getNews(): List<NewsResponse>
    suspend fun getProfile(): ProfileResponse
    suspend fun getCachedNews(): List<NewsEntity>
    suspend fun getCachedProfile(): List<ProfileEntity>
    suspend fun insertNews(news: List<NewsEntity>)
    suspend fun insertProfile(profile: ProfileEntity)
    suspend fun deleteCachedNews()
    suspend fun deleteCachedProfile()
}