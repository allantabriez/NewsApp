package com.example.newsapp.data.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SessionManager(private val context: Context) {

    companion object {
        const val TOKEN = "token"
        const val SCHEME = "scheme"
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

    fun saveSession(token: String, scheme: String, expiredAt: String) = preferences.edit {
        putString(TOKEN, token)
        putString(SCHEME, scheme)
        putString(EXPIRED_AT, expiredAt)
    }

    fun deleteSession() = preferences.all.clear()
}