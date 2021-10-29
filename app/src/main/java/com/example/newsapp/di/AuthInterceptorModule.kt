package com.example.newsapp.di

import com.example.newsapp.data.remote.network.AuthInterceptor
import org.koin.dsl.module


val authInterceptModule = module {
    single { AuthInterceptor() }
}