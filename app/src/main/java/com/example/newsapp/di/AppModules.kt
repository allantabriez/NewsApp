package com.example.newsapp.di

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.network.AuthInterceptor
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.utils.Constants.JSON_MEDIA_TYPE
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val authInterceptModule = module {
    single { AuthInterceptor() }
}

val okhttpModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(get<AuthInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}

@ExperimentalSerializationApi
val retrofitModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .client(get())
            .build()
        retrofit.create(LoginService::class.java)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .client(get())
            .build()
        retrofit.create(MeService::class.java)
    }
}