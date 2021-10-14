package com.example.newsapp.di

import com.example.newsapp.data.remote.network.AuthInterceptor
import com.example.newsapp.di.KoinConstants.LOGIN_LOCAL
import org.koin.core.qualifier.named
import org.koin.dsl.module


val authInterceptModule = module {
    single { AuthInterceptor(get(named(LOGIN_LOCAL))) }
}