package one.reevdev.stood.features.task.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.status.StatusFilterSelector
import one.reevdev.stood.features.task.component.task.TaskItem
import one.reevdev.stood.features.task.component.task.TaskToolbar
import one.reevdev.stood.features.task.screen.add.Divider

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    uiState: TaskUiState,
    snackbarHostState: SnackbarHostState,
    onStatusSelect: (TaskStatus) -> Unit,
    navigateToDetail: (id: String) -> Unit,
    navigateToAddTask: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TaskToolbar(title = stringResource(R.string.title_task_feature)) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    StatusFilterSelector(
                        modifier = Modifier,
                        statusList = TaskStatus.values().toList(),
                        isSelectedStatus = { uiState.filter == it },
                        onStatusSelect = onStatusSelect
                    )
                }
                item {
                    Divider()
                }
                taskList(
                    scope = this,
                    uiState = uiState,
                    navigateToDetail = navigateToDetail
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                onClick = navigateToAddTask
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.content_description_add_button)
                )
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

fun taskList(
    scope: LazyListScope,
    uiState: TaskUiState,
    navigateToDetail: (id: String) -> Unit
) {
    when (uiState.filter) {
        TaskStatus.ToDo -> {
            uiState.todoTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_to_do_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
        }

        TaskStatus.OnGoing -> {
            uiState.onGoingTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_on_going_task)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
        }

        TaskStatus.Done -> {
            uiState.doneTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_done_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
        }

        TaskStatus.All -> {
            uiState.todoTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_to_do_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
            scope.item { Spacer(modifier = Modifier.height(16.dp)) }
            uiState.onGoingTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_on_going_task)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
            scope.item { Spacer(modifier = Modifier.height(16.dp)) }
            uiState.doneTasks?.let { tasks ->
                scope.item { Text(text = stringResource(R.string.label_done_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(scope = scope, tasks = tasks, navigateToDetail = navigateToDetail)
            }
        }
    }
}

@Composable
fun EmptyTaskText(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.message_no_task_done)
) {
    Text(
        modifier = modifier
            .alpha(0.5f),
        text = message,
        style = MaterialTheme.typography.labelLarge
    )
}

fun taskItems(
    scope: LazyListScope,
    tasks: List<Task>,
    navigateToDetail: (id: String) -> Unit
) {
    scope.itemsIndexed(items = tasks, key = { index, item -> "${item.id}$index" }) { _, item ->
        TaskItem(
            title = item.title,
            priority = item.priority,
            hour = item.time.time,
            date = item.time.date,
            navigateToDetail = {
                navigateToDetail(item.id)
            }
        )
    }
}