package com.example.newsapp.utils

class UnusedFunctionThrowable() : Throwable("Function not used in this implementation") {
}

class NetworkThrowable(message: String?, code: ErrorCode): Throwable(message) {
    val errorCode = code
}