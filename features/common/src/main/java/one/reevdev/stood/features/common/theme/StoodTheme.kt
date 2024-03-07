package one.reevdev.stood.features.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun StoodTheme(
    colorScheme: StoodColors = StoodTheme.colors,
    typography: StoodType = StoodTheme.types,
    content: @Composable () -> Unit
) {

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