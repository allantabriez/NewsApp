package com.example.newsapp.utils

object DataMapper {
    fun <T> handleError(e: Throwable): Resource<T> {
        return if (e is NetworkThrowable) Resource.Error(code = e.errorCode)
        else Resource.Error()
    }
}