package com.example.newsapp.utils

import java.text.SimpleDateFormat

object DateUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val NEWS_DATE_FORMAT = "d MMM yyyy"

    fun toNewsDate(date: String): String {
        return try {
            val time = SimpleDateFormat(DATE_FORMAT).parse(date)
            val format = SimpleDateFormat(NEWS_DATE_FORMAT)
            format.format(time)
        } catch (e: Exception) {
            ""
        }
    }
}