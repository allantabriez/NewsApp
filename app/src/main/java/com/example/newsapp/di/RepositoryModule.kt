package com.example.newsapp.di

import com.example.newsapp.data.LoginRepositoryImpl
import com.example.newsapp.data.MeRepositoryImpl
import com.example.newsapp.data.local.MeLocalDataSource
import com.example.newsapp.data.local.SessionManager
import com.example.newsapp.data.remote.LoginRemoteDataSource
import com.example.newsapp.data.remote.MeRemoteDataSource
import com.example.newsapp.domain.repository.LoginRepository
import com.example.newsapp.domain.repository.MeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl(
            get<SessionManager>(), get<LoginRemoteDataSource>()
        )
    }
    single<MeRepository> {
        MeRepositoryImpl(
            get<MeLocalDataSource>(), get<MeRemoteDataSource>()
        )
    }
}