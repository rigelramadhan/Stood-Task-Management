package one.reevdev.cosmoe.utils

import androidx.compose.ui.graphics.Color

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