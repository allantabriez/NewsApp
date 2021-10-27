package com.example.newsapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.utils.Constants.DB_VERSION

@Database(entities = [NewsEntity::class, ProfileEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    abstract fun profileDao(): ProfileDao
}