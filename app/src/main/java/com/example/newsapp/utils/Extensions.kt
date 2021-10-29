package com.example.newsapp.utils

import android.util.Patterns
import androidx.lifecycle.ViewModel

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
