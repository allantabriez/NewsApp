package com.example.newsapp.di

import com.example.newsapp.data.local.MeLocalDataSource
import com.example.newsapp.data.local.SessionManager
import com.example.newsapp.data.remote.LoginRemoteDataSource
import com.example.newsapp.data.remote.MeRemoteDataSource
import com.example.newsapp.di.KoinConstants.LOGIN_LOCAL
import com.example.newsapp.di.KoinConstants.LOGIN_REMOTE
import com.example.newsapp.di.KoinConstants.ME_LOCAL
import com.example.newsapp.di.KoinConstants.ME_REMOTE
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single(named(LOGIN_REMOTE)) { LoginRemoteDataSource(get(), Dispatchers.IO) }
    single(named(ME_REMOTE)) { MeRemoteDataSource(get(), Dispatchers.IO) }
    single(named(LOGIN_LOCAL)) { SessionManager(androidContext()) }
    single(named(ME_LOCAL)) { MeLocalDataSource(get(), get(), Dispatchers.IO) }
}