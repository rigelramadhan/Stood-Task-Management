package one.reevdev.stood.features.task.component.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.R

@Composable
fun StatusFilterSelector(
    modifier: Modifier = Modifier,
    statusList: List<TaskStatus> = TaskStatus.values().toList().dropWhile { it == TaskStatus.All },
    isSelectedStatus: (TaskStatus) -> Boolean,
    onStatusSelect: ((TaskStatus) -> Unit)? = null,
) {
    Column(
        modifier = modifier
    ) {
        val statusLabel =
            if (onStatusSelect != null) stringResource(R.string.label_select_status)
            else stringResource(R.string.label_status)
        Text(text = statusLabel)
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = statusList, key = { it.key }) {
                FilterChip(
                    modifier = Modifier,
                    selected = isSelectedStatus(it),
                    onClick = { onStatusSelect?.invoke(it) },
                    label = { Text(it.label) },
                    shape = CircleShape
                )
            }
        }
    }
}