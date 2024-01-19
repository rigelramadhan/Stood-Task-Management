package one.reevdev.stood.features.task.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.status.StatusFilterSelector
import one.reevdev.stood.features.task.component.task.TaskItem
import one.reevdev.stood.features.task.component.widget.TaskCounterCard

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    uiState: TaskUiState,
    snackbarHostState: SnackbarHostState,
    onStatusSelect: (TaskStatus) -> Unit,
    navigateToDetail: (id: String) -> Unit,
    navigateToAddTask: () -> Unit,
    onTaskUpdate: (Task) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
                    HomeDateDisplay(
                        modifier = Modifier.padding(vertical = 18.dp),
                        day = uiState.day,
                        month = uiState.month,
                        year = uiState.year
                    )
                }
                item {
                    TaskCountCards(
                        modifier
                            .padding(vertical = 12.dp),
                        incompleteCounts = uiState.todoTasks?.size.orDefault(0),
                        ongoingCounts = uiState.onGoingTasks?.size.orDefault(0),
                        completedCounts = uiState.doneTasks?.size.orDefault(0)
                    )
                }
                item {
                    StatusFilterSelector(
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                        statusList = TaskStatus.values().toList(),
                        isSelectedStatus = { uiState.filter == it },
                        onStatusSelect = onStatusSelect
                    )
                }
                taskList(
                    scope = this,
                    uiState = uiState,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
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
    navigateToDetail: (id: String) -> Unit,
    onTaskUpdate: (Task) -> Unit,
) {
    when (uiState.filter) {
        TaskStatus.ToDo -> {
            uiState.todoTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_to_do_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
        }

        TaskStatus.OnGoing -> {
            uiState.onGoingTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_on_going_task)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
        }

        TaskStatus.Done -> {
            uiState.doneTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_done_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
        }

        TaskStatus.All -> {
            uiState.todoTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_to_do_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
            scope.item { Spacer(modifier = Modifier.height(16.dp)) }
            uiState.onGoingTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_on_going_task)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
            scope.item { Spacer(modifier = Modifier.height(16.dp)) }
            uiState.doneTasks?.let { tasks ->
                scope.item { TaskLabel(text = stringResource(R.string.label_done_tasks)) }
                if (tasks.isEmpty()) scope.item {
                    EmptyTaskText()
                }
                taskItems(
                    scope = scope,
                    tasks = tasks,
                    navigateToDetail = navigateToDetail,
                    onTaskUpdate = onTaskUpdate
                )
            }
        }
    }
}

@Composable
fun TaskLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
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
    navigateToDetail: (id: String) -> Unit,
    onTaskUpdate: (Task) -> Unit,
) {
    scope.itemsIndexed(items = tasks, key = { index, item -> "${item.id}$index" }) { _, item ->
        TaskItem(
            task = item,
            navigateToDetail = {
                navigateToDetail(item.id)
            },
            onStatusChange = {
                onTaskUpdate(item.copy(status = it))
            }
        )
    }
}

@Composable
fun TaskCountCards(
    modifier: Modifier = Modifier,
    incompleteCounts: Int,
    ongoingCounts: Int,
    completedCounts: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TaskCounterCard(
            modifier.weight(1f),
            title = stringResource(R.string.value_task_incomplete),
            count = incompleteCounts,
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        )
        TaskCounterCard(
            modifier.weight(1f),
            title = stringResource(R.string.value_task_ongoing),
            count = ongoingCounts,
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        )
        TaskCounterCard(
            modifier.weight(1f),
            title = stringResource(R.string.value_task_completed),
            count = completedCounts,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun HomeDateDisplay(
    modifier: Modifier = Modifier,
    day: String?,
    month: String?,
    year: String?,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        day?.let {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                text = it,
                style = MaterialTheme.typography.displaySmall
            )
        }
        month?.let {
            Text(
                modifier = Modifier
                    .padding(bottom = 2.dp),
                text = it,
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Normal)
            )
        }
        year?.let {
            Text(
                modifier = Modifier,
                text = it,
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}