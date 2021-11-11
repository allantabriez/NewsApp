package com.example.newsapp.utils

import java.text.SimpleDateFormat

fun String.toNewsDate(): String = try {
    val time = SimpleDateFormat(Constants.DATE_FORMAT).parse(this)
    val format = SimpleDateFormat(Constants.NEWS_DATE_FORMAT)
    format.format(time)
} catch (e: Exception) {
    ""
}