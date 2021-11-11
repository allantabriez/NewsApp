package com.example.newsapp.utils

sealed class Resource<T>(val data: T? = null, val message: ErrorCode? = null) {
    class Init<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(
        code: ErrorCode = ErrorCode.CodeUnknown,
        data: T? = null
    ) : Resource<T>(data, code)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}
