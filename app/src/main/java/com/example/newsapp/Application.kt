package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.networkModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.INFO)
            networkModule
        }
    }
}