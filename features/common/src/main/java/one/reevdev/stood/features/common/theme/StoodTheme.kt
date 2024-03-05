package one.reevdev.stood.features.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun StoodTheme(
    colorScheme: StoodColors = StoodTheme.colors,
    typography: Typography = androidx.compose.material3.MaterialTheme.typography,
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
}