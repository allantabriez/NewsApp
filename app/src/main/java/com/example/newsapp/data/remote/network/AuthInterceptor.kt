package com.example.newsapp.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
//    Still need to add some kind of session manager to set token in the header

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization", "TOKEN_HERE"
            ).build()
        )
    }
}