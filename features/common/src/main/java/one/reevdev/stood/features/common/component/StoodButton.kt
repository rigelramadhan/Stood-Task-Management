package one.reevdev.stood.features.common.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun StoodButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = StoodTheme.colors.primary,
            contentColor = StoodTheme.colors.onPrimary
        ),
        content = content
    )
}