package com.example.newsapp.repository.fake

import com.example.newsapp.data.DummyData
import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeSuccessMeDataSource: MeDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        return Json.decodeFromString<DataResponse<List<NewsResponse>>>(DummyData.NEWS_200).data
    }

    override suspend fun getProfile(): ProfileResponse {
        return Json.decodeFromString(DummyData.PROFILE_200)
    }

    override suspend fun getCachedNews(): List<NewsEntity> {
        return listOf(DummyData.NEWS_ENTITY)
    }

    override suspend fun getCachedProfile(): List<ProfileEntity> {
        return listOf(DummyData.PROFILE_ENTITY)
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