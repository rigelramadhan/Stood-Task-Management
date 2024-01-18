package one.reevdev.stood.features.task.utils

import androidx.compose.ui.graphics.Color
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

fun String.toComposeColor(): Color {
    require(startsWith("#")) { "Hex code must start with '#': $this" }
    val hex = substring(1)
    require(hex.length == 6) { "Invalid hex code length: $hex" }

    val longValue = hex.toLong(16)
    val red = (longValue shr 16 and 0xFF).toInt()
    val green = (longValue shr 8 and 0xFF).toInt()
    val blue = (longValue and 0xFF).toInt()

    return Color(red, green, blue)
}

fun Int?.orDefault(num: Int): Int = this ?: num