package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.authInterceptModule
import com.example.newsapp.di.okhttpModule
import com.example.newsapp.di.retrofitModule
import com.example.newsapp.di.viewModelModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalSerializationApi
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.INFO)
            modules(
                listOf(
                    authInterceptModule,
                    okhttpModule,
                    retrofitModule,
                    viewModelModule,
                )
            )
        }
    }
}