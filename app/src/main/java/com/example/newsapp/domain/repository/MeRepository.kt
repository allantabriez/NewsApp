package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MeRepository {
    suspend fun getNews(): Flow<Resource<List<News>>>
    suspend fun getProfile(): Flow<Resource<Profile>>
}