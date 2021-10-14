package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.*
import com.example.newsapp.utils.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_DEBUG) androidLogger()
            androidContext(this@Application)
            authInterceptModule
            okhttpModule
            retrofitModule
            dataSourceModule
            roomModule
        }
    }
}