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
    // Text colors
    val textColor: Color,
    val textDisabled: Color,
    // State colors
    val primaryUnfocused: Color,
    val primaryFocused: Color,
)


val LocalLightStoodColors = staticCompositionLocalOf {
    StoodColors(
        background = lightBackground,
        onBackground = lightOnBackground,
        surface = lightSurface,
        onSurface = lightOnSurface,
        outline = lightOutline,
        surfaceVariant = lightSurfaceVariant,
        onSurfaceVariant = lightOnSurfaceVariant,
        primary = lightPrimary,
        onPrimary = lightOnPrimary,
        primaryContainer = lightPrimaryContainer,
        onPrimaryContainer = lightOnPrimaryContainer,
        secondary = lightSecondary,
        onSecondary = lightOnSecondary,
        secondaryContainer = lightSecondaryContainer,
        onSecondaryContainer = lightOnSecondaryContainer,
        tertiary = lightTertiary,
        onTertiary = lightOnTertiary,
        tertiaryContainer = lightTertiaryContainer,
        onTertiaryContainer = lightOnTertiaryContainer,
        error = lightError,
        onError = lightOnError,
        errorContainer = lightErrorContainer,
        onErrorContainer = lightOnErrorContainer,
        eggShell = lightEggShell,
        burntSienna = lightBurntSienna,
        delftBlue = lightDelftBlue,
        cambridgeBlue = lightCambridgeBlue,
        sunset = lightSunset,
        textColor = lightTextColor,
        textDisabled = lightTextDisabled,
        primaryUnfocused = lightPrimaryUnfocused,
        primaryFocused = lightPrimaryFocused
    )
}

val LocalDarkStoodColors = staticCompositionLocalOf {
    StoodColors(
        background = darkBackground,
        onBackground = darkOnBackground,
        surface = darkSurface,
        onSurface = darkOnSurface,
        outline = darkOutline,
        surfaceVariant = darkSurfaceVariant,
        onSurfaceVariant = darkOnSurfaceVariant,
        primary = darkPrimary,
        onPrimary = darkOnPrimary,
        primaryContainer = darkPrimaryContainer,
        onPrimaryContainer = darkOnPrimaryContainer,
        secondary = darkSecondary,
        onSecondary = darkOnSecondary,
        secondaryContainer = darkSecondaryContainer,
        onSecondaryContainer = darkOnSecondaryContainer,
        tertiary = darkTertiary,
        onTertiary = darkOnTertiary,
        tertiaryContainer = darkTertiaryContainer,
        onTertiaryContainer = darkOnTertiaryContainer,
        error = darkError,
        onError = darkOnError,
        errorContainer = darkErrorContainer,
        onErrorContainer = darkOnErrorContainer,
        eggShell = darkEggShell,
        burntSienna = darkBurntSienna,
        delftBlue = darkDelftBlue,
        cambridgeBlue = darkCambridgeBlue,
        sunset = darkSunset,
        textColor = darkTextColor,
        textDisabled = darkTextDisabled,
        primaryUnfocused = darkPrimaryUnfocused,
        primaryFocused = darkPrimaryFocused
    )
}