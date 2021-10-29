package com.example.newsapp.di

import com.example.newsapp.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { IsLoggedInUseCase(get()) }
    factory { RefreshTokenUseCase(get()) }
    factory { GetNewsUseCase(get()) }
    factory { GetProfileUseCase(get()) }
}