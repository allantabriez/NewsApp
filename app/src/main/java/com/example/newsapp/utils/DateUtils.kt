package com.example.newsapp.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {
    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val NEWS_DATE_FORMAT = "d MMM yyyy"
    fun isDateAfterCurrentTime(date: String): Boolean {
        return try {
            SimpleDateFormat(DATE_FORMAT).parse(date)
                .after(Calendar.getInstance().time)
        } catch (e: Exception) {
            true
        }
    }

    fun between1HourAndNow(date: String?): Boolean {
        return try {
            val time = SimpleDateFormat(DATE_FORMAT).parse(date!!)
            Log.d("EXPIRED_TIME", time.toString())
            val currentTime = Calendar.getInstance()
            Log.d("CURRENT_TIME", currentTime.time.toString())
            time!!.before(currentTime.time) && time.after(currentTime.apply {
                add(Calendar.HOUR_OF_DAY, -1)
            }.time)
        } catch (e: Exception) {
            false
        }
    }

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