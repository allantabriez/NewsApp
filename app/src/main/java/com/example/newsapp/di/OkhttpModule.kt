package com.example.newsapp.di

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val okhttpModule = module {
    single {
        val okhttp = OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okhttp.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        okhttp.build()
    }
}