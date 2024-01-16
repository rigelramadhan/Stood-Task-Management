package one.reevdev.stood.features.task.component.task

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.core.domain.task.model.TaskTime
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.status.StatusFilterSelector
import one.reevdev.stood.features.task.screen.add.Divider
import one.reevdev.stood.features.task.theme.StoodTheme
import one.reevdev.stood.features.task.utils.toComposeColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    navigateToDetail: () -> Unit,
    onStatusChange: (TaskStatus) -> Unit,
) {
    val isExpanded = remember {
        MutableTransitionState(false)
    }

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
                .combinedClickable(
                    onClick = { isExpanded.targetState = !isExpanded.currentState },
                    onLongClick = {
                        // todo: implement select tasks
                    }
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 14.dp),
                verticalAlignment = CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(4.dp)
                        .clip(CircleShape)
                        .background(color = task.priority.color.toComposeColor())
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                AnimatedVisibility(visibleState = isExpanded) {
                    Card(
                        modifier = Modifier
                            .align(CenterVertically)
                            .clickable {
                                navigateToDetail()
                            },
                        shape = CircleShape,

                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 2.dp, horizontal = 12.dp),
                            text = "See Details"
                        )
                    }
                }
            }
            AnimatedVisibility(visibleState = isExpanded) {
                Column {
                    Row(modifier = Modifier.padding(horizontal = 18.dp)) {
                        HourAndDateText(
                            label = stringResource(R.string.label_due_time),
                            modifier = Modifier
                                .weight(1f),
                            hour = task.time.time,
                            date = task.time.date
                        )
                        Column(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "P${task.priority.priorityLevel}",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Light,
                                )
                            )
                        }
                    }
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
                            isExpanded.targetState = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HourAndDateText(
    modifier: Modifier = Modifier,
    label: String? = null,
    hour: String,
    date: String,
) {
    Column(modifier = modifier) {
        label?.let {
            Text(
                modifier = Modifier,
                text = it
            )
        }
        Row(
            modifier = Modifier
        ) {
            Text(
                modifier = Modifier,
                text = date,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier,
                text = hour,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun TaskItemPreview() {
    StoodTheme {
        Surface {
            TaskItem(
                task = Task(
                    id = "",
                    title = "Do Chores",
                    priority = TaskPriority.Normal,
                    time = TaskTime("", "20.24", "24 January 2024"),
                    category = Category("", "Work", "#2ff2de"),
                    status = TaskStatus.ToDo
                ),
                navigateToDetail = {},
                onStatusChange = {}
            )
        }
    }
}