package com.example.newsapp.di

import com.example.newsapp.data.local.MeLocalDataSource
import com.example.newsapp.data.local.SessionManager
import com.example.newsapp.data.remote.LoginRemoteDataSource
import com.example.newsapp.data.remote.MeRemoteDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataSourceModule = module {
    single { LoginRemoteDataSource(get(), Dispatchers.IO) }
    single { MeRemoteDataSource(get(), Dispatchers.IO) }
    single { SessionManager(get()) }
    single { MeLocalDataSource(get(), get(), Dispatchers.IO) }
}