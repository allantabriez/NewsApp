package com.example.newsapp.di

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
val retrofitModule = module {
    single { buildRetrofit(get()) }
    single { createRetrofitApi(get(), LoginService::class.java) }
    single { createRetrofitApi(get(), MeService::class.java) }
}

@ExperimentalSerializationApi
private fun buildRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(Json.asConverterFactory(Constants.JSON_MEDIA_TYPE.toMediaType()))
        .client(client)
        .build()
}

private fun <T> createRetrofitApi(retrofit: Retrofit, apiService: Class<T>): T {
    return retrofit.create(apiService)
}