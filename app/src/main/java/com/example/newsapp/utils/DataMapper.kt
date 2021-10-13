package com.example.newsapp.utils

object DataMapper {
    fun mapErrorCode(code: Int) = when(code) {
        200 -> ErrorCode.Code200
        401 -> ErrorCode.Code401
        422 -> ErrorCode.Code422
        else -> ErrorCode.Code400
    }
}