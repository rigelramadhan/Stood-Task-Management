package one.reevdev.stood.features.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun StoodTheme(
    colorScheme: StoodColors = StoodTheme.colors,
    typography: StoodType = StoodTheme.types,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    if (darkTheme) {
        CompositionLocalProvider(
            LocalDarkStoodColors provides colorScheme,
            LocalDarkStoodType provides typography,
            content = content,
        )
    } else {
        CompositionLocalProvider(
            LocalLightStoodColors provides colorScheme,
            LocalLightStoodType provides typography,
            content = content,
        )
    }
}

object StoodTheme {
    val colors: StoodColors
        @Composable
        @ReadOnlyComposable
        get() {
            return if (isSystemInDarkTheme()) {
                LocalDarkStoodColors.current
            } else {
                LocalLightStoodColors.current
            }
        }

    val types: StoodType
        @Composable
        @ReadOnlyComposable
        get() {
            return if (isSystemInDarkTheme()) {
                LocalDarkStoodType.current
            } else {
                LocalLightStoodType.current
            }
        }
}