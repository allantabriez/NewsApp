package com.example.newsapp.modules

import com.example.newsapp.data.remote.LoginRemoteDataSource
import com.example.newsapp.data.remote.MeRemoteDataSource
import com.example.newsapp.data.remote.network.LoginService
import com.example.newsapp.data.remote.network.MeService
import com.example.newsapp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val serverModule = module {
    single { MockWebServer() }
}

val okhttpTestModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
    }
}

@ExperimentalSerializationApi
val retrofitTestModule = module {
    single { buildRetrofit(get(), get()) }
    single { createRetrofitApi(get(), LoginService::class.java) }
    single { createRetrofitApi(get(), MeService::class.java) }
}

@ExperimentalCoroutinesApi
val remoteSourceTestModule = module {
    single { LoginRemoteDataSource(get(), TestCoroutineDispatcher()) }
    single { MeRemoteDataSource(get(), TestCoroutineDispatcher()) }
}

@ExperimentalSerializationApi
private fun buildRetrofit(client: OkHttpClient, server: MockWebServer): Retrofit {
    return Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addConverterFactory(Json.asConverterFactory(Constants.JSON_MEDIA_TYPE.toMediaType()))
        .client(client)
        .build()
}

private fun <T> createRetrofitApi(retrofit: Retrofit, apiService: Class<T>): T {
    return retrofit.create(apiService)
}