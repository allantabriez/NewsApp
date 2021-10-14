package com.example.newsapp.di

import androidx.room.Room
import com.example.newsapp.data.local.room.AppDatabase
import com.example.newsapp.utils.Constants.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }
    factory { get<AppDatabase>().newsDao() }
    factory { get<AppDatabase>().profileDao() }
}