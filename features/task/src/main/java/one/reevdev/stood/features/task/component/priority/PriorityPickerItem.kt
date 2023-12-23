package one.reevdev.stood.features.task.component.priority

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.features.task.utils.toComposeColor

@Composable
fun PriorityPickerItem(
    modifier: Modifier = Modifier,
    priority: TaskPriority,
    onPriorityClick: (priority: TaskPriority) -> Unit,
) {
    Card(
        modifier = modifier
            .clickable {
                onPriorityClick(priority)
            },
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp, end = 64.dp),
                text = priority.priorityLabel,
            )
            val brush = Brush.horizontalGradient(
                listOf(
                    Color.Transparent,
                    priority.color.toComposeColor()
                )
            )
            Box(
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .requiredWidth(64.dp)
                    .background(brush)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview
@Composable
fun PriorityPickerItemPreview() {
    MaterialTheme {
        PriorityPickerItem(
            modifier = Modifier
                .fillMaxWidth(),
            priority = TaskPriority.High,
        ) {}
    }
}