package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.utils.Resource

interface MeRepository {
    suspend fun getNews(): Resource<List<News>>
    suspend fun getProfile(): Resource<Profile>
}