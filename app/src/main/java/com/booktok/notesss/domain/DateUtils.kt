package com.booktok.notesss.domain

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun formatDate(date: Date): String {
    return when {
        isToday(date) -> "Сегодня"
        isYesterday(date) -> "Вчера"
        else -> {
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            formatter.format(date)
        }
    }
}

fun isToday(date: Date) : Boolean{
    val today = Date()
    return isSameDay(date, today)
}

fun isYesterday(date: Date): Boolean {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, -1) // получаем вчерашний день
    val yesterday = cal.time
    return isSameDay(date, yesterday)
}

fun isSameDay(date1: Date, date2: Date): Boolean {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return formatter.format(date1) == formatter.format(date2)
}
