package com.example.newsapp.repository.fake

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable

class FakeErrorMeDataSource(private val error: ErrorCode): MeDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        throw NetworkThrowable("Failed to load", error)
    }

    override suspend fun getProfile(): ProfileResponse {
        throw NetworkThrowable("Failed to load", error)
    }

    override suspend fun getCachedNews(): List<NewsEntity> {
        return emptyList()
    }

    override suspend fun getCachedProfile(): List<ProfileEntity> {
        return emptyList()
    }

    override suspend fun insertNews(news: List<NewsEntity>) {

    }

    override suspend fun insertProfile(profile: ProfileEntity) {

    }

    override suspend fun deleteCachedNews() {

    }

    override suspend fun deleteCachedProfile() {

    }
}