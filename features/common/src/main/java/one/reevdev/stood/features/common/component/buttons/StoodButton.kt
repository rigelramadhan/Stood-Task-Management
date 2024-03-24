package one.reevdev.stood.features.common.component.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import one.reevdev.stood.features.common.component.texts.StoodText
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun StoodButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = StoodTheme.colors.primary,
            contentColor = StoodTheme.colors.onPrimary
        ),
    ) {
        StoodText(
            text = text,
            color = StoodTheme.colors.onPrimary
        )
    }
}