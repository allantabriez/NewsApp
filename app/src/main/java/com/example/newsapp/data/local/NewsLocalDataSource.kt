package com.example.newsapp.data.local

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.room.NewsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NewsLocalDataSource(
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
}