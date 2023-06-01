package com.dzakdzaks.movieapp.common.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int?.orEmpty(defaultValue: Int = 0): Int = this ?: defaultValue

fun Double?.orEmpty(defaultValue: Double = 0.0): Double = this ?: defaultValue

fun Boolean?.orEmpty(defaultValue: Boolean = false): Boolean = this ?: defaultValue


const val FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_READABLE_PATTERN = "dd MMMM yyyy"
const val TIME_READABLE_PATTERN = "HH:mm:ss"

fun dateFormat(pattern: String): SimpleDateFormat =
    SimpleDateFormat(pattern, Locale.getDefault())

fun String.readableDateTime(): String {
    val formatter = dateFormat(FULL_DATE_FORMAT)
    val date = formatter.parse(this)

    date?.let {
        return it.toReadableDateFullFormat()
    }
    return "Unknown"
}

fun Date.toReadableDateFullFormat(): String {
    return "${this.toDateFormat()} at ${this.toTimeFormat()}"
}

fun Date.toDateFormat(): String {
    val dayDateFormatter = dateFormat(DATE_READABLE_PATTERN)
    return dayDateFormatter.format(this)
}

fun Date.toTimeFormat(): String {
    val timeDateFormatter = dateFormat(TIME_READABLE_PATTERN)
    return timeDateFormatter.format(this)
}