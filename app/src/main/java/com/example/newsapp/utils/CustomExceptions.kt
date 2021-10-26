package com.example.newsapp.utils

class UnusedFunctionException() : Exception("Function not used in this implementation") {
}

class NetworkException(message: String?, code: ErrorCode): Exception(message) {
    val errorCode = code
}