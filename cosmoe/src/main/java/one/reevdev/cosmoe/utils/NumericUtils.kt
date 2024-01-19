package one.reevdev.cosmoe.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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