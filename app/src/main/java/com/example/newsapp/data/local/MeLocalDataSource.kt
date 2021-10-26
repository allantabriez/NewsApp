package com.example.newsapp.data.local

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.ProfileDao
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MeLocalDataSource(
    private val newsDao: NewsDao,
    private val profileDao: ProfileDao,
    private val ioDispatcher: CoroutineDispatcher
) : MeDataSource {
    override suspend fun getCachedNews() = withContext(ioDispatcher) {
        return@withContext newsDao.getNews()
    }

    override suspend fun getCachedProfile() = withContext(ioDispatcher) {
        return@withContext profileDao.getProfile()
    }

    override suspend fun insertNews(news: List<NewsEntity>) = withContext(ioDispatcher) {
        newsDao.insertNews(news)
    }

    override suspend fun insertProfile(profile: ProfileEntity) = withContext(ioDispatcher) {
        profileDao.insertProfile(profile)
    }

    override suspend fun deleteCachedNews() = withContext(ioDispatcher) {
        newsDao.deleteNews()
    }

    override suspend fun deleteCachedProfile() = withContext(ioDispatcher) {
        profileDao.deleteProfile()
    }

//    Below are unused functions in this implementation

    override suspend fun getNews(): List<NewsResponse> {
        throw UnusedFunctionException()
    }

    override suspend fun getProfile(): ProfileResponse {
        throw UnusedFunctionException()
    }
}