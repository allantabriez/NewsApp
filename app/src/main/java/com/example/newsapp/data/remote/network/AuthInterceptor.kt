package com.example.newsapp.data.remote.network

import com.example.newsapp.data.LoginDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: LoginDataSource) : Interceptor {
//    Still need to add some kind of session manager to set token in the header

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                "Authorization", value = "Bearer " + sessionManager.getToken()
            ).build()
        )
    }
}