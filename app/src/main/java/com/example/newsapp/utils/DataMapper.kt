package com.example.newsapp.utils

import android.util.Log

object DataMapper {
    fun <T> handleError(e: Throwable): Resource<T> {
        Log.d("Error", e.toString())
        return if (e is NetworkThrowable) Resource.Error(code = e.errorCode)
        else Resource.Error()
    }
}