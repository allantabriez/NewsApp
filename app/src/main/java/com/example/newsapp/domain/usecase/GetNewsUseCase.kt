package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.News
import com.example.newsapp.utils.Resource

interface GetNewsUseCase {
    suspend fun getNews(): Resource<List<News>>
}