package com.example.newsapp.data

import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.domain.repository.MeRepository

class MeRepositoryImpl(
    private val localSource: MeDataSource,
    private val remoteSource: MeDataSource,
) : MeRepository {
    override suspend fun getNews(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Profile {
        TODO("Not yet implemented")
    }
}