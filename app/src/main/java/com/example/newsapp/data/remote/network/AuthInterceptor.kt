package com.example.newsapp.data.remote.network

import com.example.newsapp.domain.usecase.RefreshTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthInterceptor : Interceptor, KoinComponent {
    private val refreshTokenUseCase: RefreshTokenUseCase by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val verifiedToken = runBlocking {
            refreshTokenUseCase.invoke()
        }

        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization", value = "Bearer " + verifiedToken.token
            ).build()
        )
    }
}