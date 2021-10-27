package com.example.newsapp.data.remote

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkException
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MeRemoteDataSource(
    private val meService: MeService,
    private val ioDispatcher: CoroutineDispatcher
) : MeDataSource {

    override suspend fun getNews(): List<NewsResponse> = withContext(ioDispatcher) {
        try {
            val response = meService.getNews()
            if (response.isSuccessful) return@withContext response.body()?.data as List<NewsResponse>
            else throw NetworkException(response.message(), ErrorCode.fromInt(response.code()))
        } catch (e: Exception) {
            throw NetworkException(e.message, ErrorCode.CodeUnknown)
        }
    }

    override suspend fun getProfile(): ProfileResponse = withContext(ioDispatcher) {
        try {
            val response = meService.getProfile()
            if (response.isSuccessful) return@withContext response.body() as ProfileResponse
            else throw NetworkException(response.message(), ErrorCode.fromInt(response.code()))
        } catch (e: Exception) {
            throw NetworkException(e.message, ErrorCode.CodeUnknown)
        }
    }


//    Below are unused functions in this implementation

    override suspend fun getCachedNews(): List<NewsEntity> {
        throw UnusedFunctionException()
    }

    override suspend fun getCachedProfile(): List<ProfileEntity> {
        throw UnusedFunctionException()
    }

    override suspend fun insertNews(news: List<NewsEntity>) {
        throw UnusedFunctionException()
    }

    override suspend fun insertProfile(profile: ProfileEntity) {
        throw UnusedFunctionException()
    }

    override suspend fun deleteCachedNews() {
        throw UnusedFunctionException()
    }

    override suspend fun deleteCachedProfile() {
        throw UnusedFunctionException()
    }
}