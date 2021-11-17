package com.example.newsapp.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.UnusedFunctionThrowable

class SessionManager(private val preferences: SharedPreferences) : LoginDataSource {

    companion object {
        const val TOKEN = "token"
        const val EXPIRED_AT = "expired_at"
    }

    override fun saveSession(token: String, expiredAt: String) = preferences.edit {
        putString(TOKEN, token)
        putString(EXPIRED_AT, expiredAt)
    }

    override fun getToken() = preferences.getString(TOKEN, "")

    override fun getExpiredAt() = preferences.getString(EXPIRED_AT, "")

    override fun deleteSession() = preferences.all.clear()


//    Below are unused functions in this implementation

    override suspend fun doLogin(username: String, pass: String): TokenResponse {
        throw UnusedFunctionThrowable()
    }

    override suspend fun refreshToken(): TokenResponse {
        throw UnusedFunctionThrowable()
    }
}