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
    statusList: List<TaskStatus> = TaskStatus.values().toList(),
    selectedStatus: TaskStatus,
    isStatusSelected: (TaskStatus) -> Boolean,
    onStatusSelect: (TaskStatus) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.label_select_status))
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = statusList, key = { it.key }) {
                FilterChip(
                    modifier = Modifier,
                    selected = isStatusSelected(it),
                    onClick = { onStatusSelect(it) },
                    label = { Text(it.label) },
                    shape = CircleShape
                )
            }
        }
    }
}