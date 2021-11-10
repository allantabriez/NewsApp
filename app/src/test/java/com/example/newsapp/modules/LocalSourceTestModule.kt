package com.example.newsapp.modules

import android.content.SharedPreferences
import com.example.newsapp.data.local.MeLocalDataSource
import com.example.newsapp.data.local.SessionManager
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.ProfileDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.koin.dsl.module

fun loginLocalSourceTestModule(preferences: SharedPreferences) = module {
    single { SessionManager(preferences) }
}

@ExperimentalCoroutinesApi
fun meLocalSourceTestModule(newsDao: NewsDao, profileDao: ProfileDao) = module {
    single { MeLocalDataSource(newsDao, profileDao, TestCoroutineDispatcher()) }
}