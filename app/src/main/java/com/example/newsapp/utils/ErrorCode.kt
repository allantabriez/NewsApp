package com.example.newsapp.utils

enum class ErrorCode(val code: Int) {
    Code200(200), Code400(400), Code401(401), Code422(422), CodeUnknown(99);
    companion object {
        fun fromInt(rawValue: Int) = values().firstOrNull {
            it.code == rawValue
        } ?: CodeUnknown
    }
}
