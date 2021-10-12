package com.example.newsapp.data.local

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.ProfileDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LocalDataSource(
    private val profileDao: ProfileDao,
    private val newsDao: NewsDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getNews() = newsDao.getNews()

    fun insertNews(news: List<NewsEntity>) = CoroutineScope(ioDispatcher).launch {
        newsDao.insertNews(news)
    }

    fun deleteNews() = CoroutineScope(ioDispatcher).launch {
        newsDao.deleteNews()
    }

    fun getProfile() = profileDao.getProfile()

    fun insertProfile(profile: ProfileEntity) = CoroutineScope(ioDispatcher).launch {
        profileDao.insertProfile(profile)
    }
    
    fun deleteProfile() = CoroutineScope(ioDispatcher).launch {
        profileDao.deleteProfile()
    }
}