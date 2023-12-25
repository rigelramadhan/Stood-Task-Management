package one.reevdev.stood.features.task.component.priority

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.TaskPriority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityPickerBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    title: String? = null,
    priorities: List<TaskPriority>,
    onPriorityClick: (TaskPriority) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
    ) {
        title?.let {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                text = it,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            priorities.forEach {
                PriorityPickerItem(
                    modifier = Modifier.padding(bottom = 8.dp),
                    priority = it,
                    onPriorityClick = onPriorityClick
                )
            }
        }
    }
}