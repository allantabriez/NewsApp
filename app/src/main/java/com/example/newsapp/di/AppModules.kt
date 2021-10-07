package com.example.newsapp.di

import com.example.newsapp.data.remote.network.ApiService
import com.example.newsapp.data.remote.network.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@ExperimentalSerializationApi
val networkModule = module {
    single { AuthInterceptor() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(get<AuthInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.121.153.157/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}