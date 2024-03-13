package one.reevdev.stood.features.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import one.reevdev.stood.features.common.R

private val FONT_REGULAR = FontFamily(Font(R.font.josefin_sans_regular))
private val FONT_MEDIUM = FontFamily(Font(R.font.josefin_sans_medium))

val defaultLightTextStyle
    get() = TextStyle(
        fontFamily = FONT_REGULAR,
//        color = lightTextColor,
    )

val defaultDarkTextStyle
    get() = TextStyle(
        fontFamily = FONT_MEDIUM,
//        color = darkTextColor,
    )

@Immutable
data class StoodType(
    val bodySmall: TextStyle,
    val bodyMedium: TextStyle,
    val bodyLarge: TextStyle,
    val labelSmall: TextStyle,
    val labelMedium: TextStyle,
    val labelLarge: TextStyle,
    val titleSmall: TextStyle,
    val titleMedium: TextStyle,
    val titleLarge: TextStyle,
    val headlineSmall: TextStyle,
    val headlineMedium: TextStyle,
    val headlineLarge: TextStyle,
    val displaySmall: TextStyle,
    val displayMedium: TextStyle,
    val displayLarge: TextStyle,
)

val LocalLightStoodType = staticCompositionLocalOf {
    with(defaultLightTextStyle) {
        StoodType(
            bodySmall = copy(fontSize = 12.sp, lineHeight = 16.sp),
            bodyMedium = copy(fontSize = 14.sp, lineHeight = 20.sp),
            bodyLarge = copy(fontSize = 16.sp, lineHeight = 24.sp),
            labelSmall = copy(fontSize = 11.sp, lineHeight = 16.sp, fontFamily = FONT_MEDIUM),
            labelMedium = copy(fontSize = 12.sp, lineHeight = 16.sp, fontFamily = FONT_MEDIUM),
            labelLarge = copy(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = FONT_MEDIUM),
            titleSmall = copy(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = FONT_MEDIUM),
            titleMedium = copy(fontSize = 16.sp, lineHeight = 24.sp, fontFamily = FONT_MEDIUM),
            titleLarge = copy(fontSize = 22.sp, lineHeight = 28.sp),
            headlineSmall = copy(fontSize = 24.sp, lineHeight = 32.sp),
            headlineMedium = copy(fontSize = 28.sp, lineHeight = 36.sp),
            headlineLarge = copy(fontSize = 32.sp, lineHeight = 40.sp),
            displaySmall = copy(fontSize = 36.sp, lineHeight = 44.sp),
            displayMedium = copy(fontSize = 45.sp, lineHeight = 52.sp),
            displayLarge = copy(fontSize = 57.sp, lineHeight = 64.sp),
        )
    }
}

val LocalDarkStoodType = staticCompositionLocalOf {
    with(defaultDarkTextStyle) {
        StoodType(
            bodySmall = copy(fontSize = 12.sp, lineHeight = 16.sp),
            bodyMedium = copy(fontSize = 14.sp, lineHeight = 20.sp),
            bodyLarge = copy(fontSize = 16.sp, lineHeight = 24.sp),
            labelSmall = copy(fontSize = 11.sp, lineHeight = 16.sp, fontFamily = FONT_MEDIUM),
            labelMedium = copy(fontSize = 12.sp, lineHeight = 16.sp, fontFamily = FONT_MEDIUM),
            labelLarge = copy(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = FONT_MEDIUM),
            titleSmall = copy(fontSize = 14.sp, lineHeight = 20.sp, fontFamily = FONT_MEDIUM),
            titleMedium = copy(fontSize = 16.sp, lineHeight = 24.sp, fontFamily = FONT_MEDIUM),
            titleLarge = copy(fontSize = 22.sp, lineHeight = 28.sp),
            headlineSmall = copy(fontSize = 24.sp, lineHeight = 32.sp),
            headlineMedium = copy(fontSize = 28.sp, lineHeight = 36.sp),
            headlineLarge = copy(fontSize = 32.sp, lineHeight = 40.sp),
            displaySmall = copy(fontSize = 36.sp, lineHeight = 44.sp),
            displayMedium = copy(fontSize = 45.sp, lineHeight = 52.sp),
            displayLarge = copy(fontSize = 57.sp, lineHeight = 64.sp),
        )
    }
}