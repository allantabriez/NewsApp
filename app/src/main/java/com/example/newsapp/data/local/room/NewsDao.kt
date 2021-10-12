package com.example.newsapp.data.local.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsDao {

    @Insert(entity = NewsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>)

    @Query("SELECT * from news_table")
    fun getNews(): Flow<List<NewsEntity>>

    @Query("DELETE FROM news_table")
    fun deleteNews()
}