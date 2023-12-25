package one.reevdev.stood.features.task.component.task

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.status.StatusFilterSelector
import one.reevdev.stood.features.task.screen.add.Divider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    navigateToDetail: () -> Unit,
    onStatusChange: (TaskStatus) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray,
        )
    ) {
        Column(
            modifier = Modifier
//                .clickable { navigateToDetail() }
                .combinedClickable(
                    onClick = { navigateToDetail() },
                    onLongClick = { isExpanded = !isExpanded }
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier,
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    HourAndDateText(
                        modifier = Modifier,
                        hour = task.time.time,
                        date = task.time.date
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.label_priority),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        modifier = Modifier,
                        text = task.priority.priorityLabel,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
            }
            if (isExpanded) {
                Divider()
                StatusFilterSelector(
                    modifier = Modifier
                        .padding(start = 18.dp, end = 18.dp, bottom = 12.dp),
                    title = stringResource(R.string.message_change_status),
                    isSelectedStatus = {
                        it == task.status
                    },
                    onStatusSelect = {
                        onStatusChange(it)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun HourAndDateText(
    modifier: Modifier = Modifier,
    hour: String,
    date: String,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier,
            text = hour,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier,
            text = date,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}