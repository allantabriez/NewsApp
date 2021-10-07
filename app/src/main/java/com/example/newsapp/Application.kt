package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.networkModule
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            networkModule
        }
    }
}