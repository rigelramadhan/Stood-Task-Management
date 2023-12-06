package one.reevdev.stood.features.task.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import one.reevdev.stood.features.task.component.TaskItem

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    uiState: TaskUiState,
    navigateToDetail: (id: String) -> Unit,
) {
    uiState.tasks?.let { tasks ->
        LazyColumn(
            modifier = modifier
        ) {
            itemsIndexed(
                items = tasks,
                key = { index, item -> "${item.id}$index" }
            ) { _, item ->
                TaskItem(
                    title = item.title,
                    priority = item.priority.priorityLevel,
                    hour = item.time.time,
                    date = item.time.date,
                    navigateToDetail = {
                        navigateToDetail(item.id)
                    }
                )
            }
        }
    }
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}