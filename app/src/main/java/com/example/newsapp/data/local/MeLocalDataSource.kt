package com.example.newsapp.data.local

import com.example.newsapp.data.MeDataSource
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.ProfileDao
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MeLocalDataSource(
    private val newsDao: NewsDao,
    private val profileDao: ProfileDao,
    private val ioDispatcher: CoroutineDispatcher
) : MeDataSource {
    override fun getCachedNews() = newsDao.getNews()
    override fun getCachedProfile() = profileDao.getProfile()

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

    override fun getNews(): Flow<Resource<List<NewsResponse>>> {
        throw UnusedFunctionException()
    }

    override fun getProfile(): Flow<Resource<ProfileResponse>> {
        throw UnusedFunctionException()
    }
}