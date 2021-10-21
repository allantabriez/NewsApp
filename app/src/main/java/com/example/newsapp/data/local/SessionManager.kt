package com.example.newsapp.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.newsapp.data.LoginDataSource
import com.example.newsapp.data.remote.response.TokenResponse
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.UnusedFunctionException
import kotlinx.coroutines.flow.Flow

class SessionManager(private val context: Context) : LoginDataSource {

    companion object {
        const val TOKEN = "token"
        const val EXPIRED_AT = "expired_at"
    }

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val preferences = EncryptedSharedPreferences.create(
        "NEWS_APP",
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveSession(token: String, expiredAt: String) = preferences.edit {
        putString(TOKEN, token)
        putString(EXPIRED_AT, expiredAt)
    }

    override fun getToken() = preferences.getString(TOKEN, "")!!

    override fun getExpiredAt() = preferences.getString(EXPIRED_AT, "")!!

    override fun deleteSession() = preferences.all.clear()


//    Below are unused functions in this implementation

    override fun doLogin(username: String, pass: String): Flow<Resource<TokenResponse>> {
        throw UnusedFunctionException()
    }

    override fun refreshToken(): Flow<Resource<TokenResponse>> {
        throw UnusedFunctionException()
    }
}