package one.reevdev.stood.features.task.component.status

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.R

@Composable
fun StatusFilterSelector(
    modifier: Modifier = Modifier,
    title: String? = null,
    statusList: List<TaskStatus> = TaskStatus.values().toList().dropWhile { it == TaskStatus.All },
    isSelectedStatus: (TaskStatus) -> Boolean,
    onStatusSelect: ((TaskStatus) -> Unit)? = null,
) {
    Column(
        modifier = modifier
    ) {
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = statusList, key = { it.key }) {
                StatusFilterChip(
                    isSelectedStatus = isSelectedStatus,
                    onStatusSelect = onStatusSelect,
                    task = it
                )
            }
        }
    }
}

@Composable
fun StatusFilterChip(
    modifier: Modifier = Modifier,
    isSelectedStatus: (TaskStatus) -> Boolean,
    onStatusSelect: ((TaskStatus) -> Unit)? = null,
    task: TaskStatus,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelectedStatus(task),
        onClick = { onStatusSelect?.invoke(task) },
        label = {
            Text(
                text = task.label,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        shape = CircleShape,
        leadingIcon = {
            when (task) {
                TaskStatus.All -> TaskStatusIcon(
                    iconRes = R.drawable.ic_all,
                    contentDescription = stringResource(
                        R.string.content_description_all_task_icon
                    )
                )

                TaskStatus.ToDo -> TaskStatusIcon(
                    iconRes = R.drawable.ic_todo,
                    contentDescription = stringResource(R.string.content_description_todo_task_icon)
                )

                TaskStatus.OnGoing -> TaskStatusIcon(
                    iconRes = R.drawable.ic_ongoing,
                    contentDescription = stringResource(R.string.content_description_on_going_task_icon)
                )

                TaskStatus.Done -> TaskStatusIcon(
                    iconRes = R.drawable.ic_finished,
                    contentDescription = stringResource(R.string.content_description_done_task_icon)
                )

                else -> {}
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            labelColor = MaterialTheme.colorScheme.primary,
            iconColor = MaterialTheme.colorScheme.primary,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelectedStatus(task),
            borderWidth = 0.dp,
            selectedBorderWidth = 0.dp,
            borderColor = Color.Transparent,
            selectedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun TaskStatusIcon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    contentDescription: String,
) {
    Icon(
        modifier = modifier
            .size(16.dp),
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription,
    )
}