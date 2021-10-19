package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {
    suspend fun getNews(): Flow<Resource<List<News>>>
}