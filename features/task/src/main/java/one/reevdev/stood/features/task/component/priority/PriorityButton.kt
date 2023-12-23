package one.reevdev.stood.features.task.component.priority

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.utils.toComposeColor

@Composable
fun PriorityButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    priority: String,
    color: String,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = "Priority - $priority",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clip(CircleShape)
                .size(24.dp)
                .background(
                    color = color.toComposeColor()
                ),
        )
    }
}