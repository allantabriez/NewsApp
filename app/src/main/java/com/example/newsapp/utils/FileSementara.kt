package com.example.newsapp.utils

// This file will be deleted, because it already exist in another branch that I'm currently asking
// for review, so I haven't merged those branches yet

sealed class Resource<T>(val data: T? = null, val message: ErrorCode? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(code: ErrorCode, data: T? = null) : Resource<T>(data, code)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

enum class ErrorCode {
    Code200, Code400, Code401, Code422
}