package com.example.newsapp.data.remote

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MeRemoteDataSource(
    private val meService: MeService,
    private val ioDispatcher: CoroutineDispatcher
): MeDataSource {

    override suspend fun getNews(): Resource<List<NewsResponse>> = withContext(ioDispatcher) {
        val response = meService.getNews()
        if (response.isSuccessful) return@withContext Resource.Success(response.body()?.data as List<NewsResponse>)
        else return@withContext Resource.Error(ErrorCode.fromInt(response.code()))
    }

    override suspend fun getProfile(): Resource<ProfileResponse> = withContext(ioDispatcher) {
        val response = meService.getProfile()
        if (response.isSuccessful) return@withContext Resource.Success(response.body() as ProfileResponse)
        else return@withContext Resource.Error(ErrorCode.fromInt(response.code()))
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