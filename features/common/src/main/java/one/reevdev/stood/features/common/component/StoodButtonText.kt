package one.reevdev.stood.features.common.component

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun StoodButtonText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = StoodTheme.colors.tertiary,
    onClick: () -> Unit,
) {
    StoodText(
        modifier = modifier
            .clickable { onClick() },
        text = text,
        color = color
    )
}