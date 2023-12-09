package one.reevdev.stood.features.task.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.TaskItem
import one.reevdev.stood.features.task.component.TaskToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    uiState: TaskUiState,
    navigateToDetail: (id: String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TaskToolbar(title = stringResource(R.string.title_task_feature)) }
    ) { innerPadding ->
        uiState.tasks?.let { tasks ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}