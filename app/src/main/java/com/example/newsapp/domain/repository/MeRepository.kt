package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile

interface MeRepository {
    suspend fun getNews(): List<News>
    suspend fun getProfile(): Profile
}