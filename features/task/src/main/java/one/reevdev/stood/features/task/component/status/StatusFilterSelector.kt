package one.reevdev.stood.features.task.component.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.TaskStatus

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
                FilterChip(
                    modifier = Modifier,
                    selected = isSelectedStatus(it),
                    onClick = { onStatusSelect?.invoke(it) },
                    label = {
                        Text(
                            text = it.label,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    shape = CircleShape,
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
                        selected = isSelectedStatus(it),
                        borderWidth = 0.dp,
                        selectedBorderWidth = 0.dp,
                        borderColor = Color.Transparent,
                        selectedBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}