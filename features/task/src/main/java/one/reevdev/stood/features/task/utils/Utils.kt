package one.reevdev.stood.features.task.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int.mapToLabel(): String = when(this) {
    1 -> "Low"
    2 -> "Normal"
    3 -> "High"
    4 -> "Urgent"
    5 -> "Critical"
    else -> "Other"
}

fun Long.toFormattedDate(
    dateFormat: String = "yyyy-MM-dd HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String {
    val date = Date(this)

    return when (dateFormat) {
        "short" -> {
            DateFormat.getDateInstance(DateFormat.SHORT, locale).format(date)
        }
        "medium" -> {
            DateFormat.getDateInstance(DateFormat.MEDIUM, locale).format(date)
        }
        "long" -> {
            DateFormat.getDateInstance(DateFormat.LONG, locale).format(date)
        }
        else -> SimpleDateFormat(dateFormat, locale).format(date)
    }

}