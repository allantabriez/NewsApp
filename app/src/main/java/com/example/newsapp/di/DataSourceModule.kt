package com.example.newsapp.di

import com.example.newsapp.data.remote.LoginDataSource
import com.example.newsapp.data.remote.MeDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataSourceModule = module {
    single { LoginDataSource(get(), Dispatchers.IO) }
    single { MeDataSource(get(), Dispatchers.IO) }
}