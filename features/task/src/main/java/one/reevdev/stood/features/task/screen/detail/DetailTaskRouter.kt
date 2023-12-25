package one.reevdev.stood.features.task.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.core.domain.task.params.TaskUiParams
import one.reevdev.stood.features.task.screen.add.isDataValid
import one.reevdev.stood.features.task.utils.actions.TaskAction

@Composable
fun DetailTaskRouter(
    viewModel: DetailTaskViewModel = hiltViewModel(),
    taskId: String,
    onNavigateBack: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    var title by rememberSaveable { mutableStateOf(uiState.task?.title.orEmpty()) }
    var hour by rememberSaveable { mutableStateOf(uiState.task?.time?.time.orEmpty()) }
    var date by rememberSaveable { mutableStateOf(uiState.task?.time?.date.orEmpty()) }
    var priority by rememberSaveable { mutableStateOf(uiState.task?.priority ?: TaskPriority.Normal) }
    var status by rememberSaveable { mutableStateOf(uiState.task?.status ?: TaskStatus.ToDo) }
    var hasCategoriesBeenLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.categories) {
        if (uiState.categories.isNotEmpty()) hasCategoriesBeenLoaded = true
    }

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(message = it, withDismissAction = true)
        }
    }

    LaunchedEffect(uiState.isTaskDeleted) {
        if (uiState.isTaskDeleted) {
            onNavigateBack()
        }
    }

    LaunchedEffect(uiState.isTaskSaved) {
        if (uiState.isTaskSaved) {
            onNavigateBack()
        }
    }

    LaunchedEffect(uiState.task) {
        uiState.task?.let {
            title = it.title
            hour = it.time.time
            date = it.time.date
            priority = it.priority
            status = it.status
        }
    }

    if (hasCategoriesBeenLoaded) {
        var category by remember {
            mutableStateOf(uiState.categories.first())
        }

        val taskParams = TaskUiParams(
            title = title,
            time = hour,
            date = date,
            priority = priority,
            category = category,
            status = status
        )

        val taskAction = TaskAction(
            onTitleChange = { title = it },
            onHourChange = { hour = it },
            onDateChange = { date = it },
            onPriorityChange = { priority = it },
            onCategoryChange = { category = it },
            onStatusChange = { status = it },
            onSaveTask = {
                if (isDataValid(title, hour, date))
                    viewModel.updateTask(taskId, taskParams)
                else {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Please fill out the data correctly",
                            withDismissAction = true
                        )
                    }
                }
            }
        )

        DetailTaskScreen(
            uiState = uiState,
            snackbarHostState = snackbarHostState,
            taskParams = taskParams,
            categoryList = uiState.categories,
            taskAction = taskAction,
            onDeleteTask = { viewModel.deleteTaskById(taskId) },
            onNavigateBack = {
                taskAction.onSaveTask()
                onNavigateBack()
            }
        )

        BackHandler {
            taskAction.onSaveTask()
            onNavigateBack()
        }
    }
}