package com.example.newsapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(entity = NewsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>)

    @Query("SELECT * from news_table")
    fun getNews(): List<NewsEntity>

    @Query("DELETE FROM news_table")
    fun deleteNews()
}