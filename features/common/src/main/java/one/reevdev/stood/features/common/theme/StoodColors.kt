package one.reevdev.stood.features.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class StoodColors(
    // Primary colors
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val outline: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    // Accent colors
    val eggShell: Color,
    val burntSienna: Color,
    val delftBlue: Color,
    val cambridgeBlue: Color,
    val sunset: Color,
)

val LocalLightStoodColors = staticCompositionLocalOf {
    StoodColors(
        background = Color(0xFFF1F5FF),
        onBackground = Color(0xFFD9E0F0),
        surface = Color(0xFFD0E0F2),
        onSurface = Color(0xFF1A1B1F),
        outline = Color(0xFFEAEAEA),
        surfaceVariant = Color(0xFFB5CFEC),
        onSurfaceVariant = Color(0xFF44474E),
        primary = Color(0xFFFAFBFF),
        onPrimary = Color(0xFF7C818E),
        primaryContainer = Color(0xFFE1EAFF),
        onPrimaryContainer = Color(0xFFCDDCFF),
        secondary = Color(0xFFF6EDFF),
        onSecondary = Color(0xFFE8DAF5),
        secondaryContainer = Color(0xFFF6EDFF),
        onSecondaryContainer = Color(0xFFE9DBF6),
        tertiary = Color(0xFFCFE1B9),
        onTertiary = Color(0xFF9BBA6C),
        tertiaryContainer = Color(0xFFE2EBD2),
        onTertiaryContainer = Color(0xFFB5C99A),
        error = Color(0xFFBA1A1A),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        eggShell = Color(0xFFF4F1DE),
        burntSienna = Color(0xFFE07A5F),
        delftBlue = Color(0xFF3D405B),
        cambridgeBlue = Color(0xFF81B29A),
        sunset = Color(0xFFF2CC8F),
    )
}

// UNFINISHED
val LocalDarkStoodColors = staticCompositionLocalOf {
    StoodColors(
        background = Color(0xFFF1F5FF),
        onBackground = Color(0xFFD9E0F0),
        surface = Color(0xFFD0E0F2),
        onSurface = Color(0xFF1A1B1F),
        outline = Color(0xFFEAEAEA),
        surfaceVariant = Color(0xFFB5CFEC),
        onSurfaceVariant = Color(0xFF44474E),
        primary = Color(0xFFFAFBFF),
        onPrimary = Color(0xFF7C818E),
        primaryContainer = Color(0xFFE1EAFF),
        onPrimaryContainer = Color(0xFFCDDCFF),
        secondary = Color(0xFFF6EDFF),
        onSecondary = Color(0xFFE8DAF5),
        secondaryContainer = Color(0xFFF6EDFF),
        onSecondaryContainer = Color(0xFFE9DBF6),
        tertiary = Color(0xFFCFE1B9),
        onTertiary = Color(0xFF9BBA6C),
        tertiaryContainer = Color(0xFFE2EBD2),
        onTertiaryContainer = Color(0xFFB5C99A),
        error = Color(0xFFBA1A1A),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        eggShell = Color(0xFFF4F1DE),
        burntSienna = Color(0xFFE07A5F),
        delftBlue = Color(0xFF3D405B),
        cambridgeBlue = Color(0xFF81B29A),
        sunset = Color(0xFFF2CC8F),
    )
}