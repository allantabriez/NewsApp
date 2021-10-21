package com.example.newsapp.data.remote

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.DataMapper
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MeRemoteDataSource(
    private val meService: MeService,
    private val ioDispatcher: CoroutineDispatcher
): MeDataSource {

    override fun getNews(): Flow<Resource<List<NewsResponse>>> {
        return flow {
            val response = meService.getNews()
            if (response.isSuccessful) emit(Resource.Success(response.body()?.data as List<NewsResponse>))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }

    override fun getProfile(): Flow<Resource<ProfileResponse>> {
        return flow {
            val response = meService.getProfile()
            if (response.isSuccessful) emit(Resource.Success(response.body() as ProfileResponse))
            else emit(Resource.Error(DataMapper.mapErrorCode(response.code())))
        }.flowOn(ioDispatcher)
    }


//    Below are unused functions in this implementation

    override fun getCachedNews(): Flow<List<NewsEntity>> {
        throw UnusedFunctionException()
    }

    override fun getCachedProfile(): Flow<List<ProfileEntity>> {
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