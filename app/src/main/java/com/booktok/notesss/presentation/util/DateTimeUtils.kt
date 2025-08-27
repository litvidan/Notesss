package com.booktok.notesss.presentation.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import com.booktok.notesss.R


fun formatDate(context: Context, date: Date): String {
    return when {
        isToday(date) -> context.getString(R.string.today)
        isYesterday(date) -> context.getString(R.string.yesterday)
        else -> {
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            formatter.format(date)
        }
    }
}

fun formatTime(date: Date): String{
    val hours = date.hours
    val minutes = date.minutes

    if(minutes < 10) return "${hours}:0${minutes}"
    else return "${hours}:${minutes}"
}

fun isToday(date: Date) : Boolean{
    val today = Date()
    return isSameDay(date, today)
}

fun isYesterday(date: Date): Boolean {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, -1)
    val yesterday = cal.time
    return isSameDay(date, yesterday)
}

fun isSameDay(date1: Date, date2: Date): Boolean {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return formatter.format(date1) == formatter.format(date2)
}
