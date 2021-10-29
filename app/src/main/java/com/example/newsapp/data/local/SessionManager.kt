package com.example.newsapp.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Constants.PREFS_NAME
import com.example.newsapp.utils.UnusedFunctionThrowable

class SessionManager(private val context: Context) : LoginDataSource {

    companion object {
        const val TOKEN = "token"
        const val EXPIRED_AT = "expired_at"
    }

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val preferences = EncryptedSharedPreferences.create(
        PREFS_NAME,
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

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