package one.reevdev.stood.features.task.screen.add

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
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.TaskStatus
import one.reevdev.stood.core.domain.task.params.TaskUiParams

@Composable
fun AddTaskRouter(
    viewModel: AddTaskViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    defaultCategory: Category = Category("default_general", "General", "#5BC8F4"),
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    var title by rememberSaveable { mutableStateOf("") }
    var hour by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var priority by rememberSaveable { mutableStateOf(2) }
    var status by rememberSaveable { mutableStateOf(TaskStatus.ToDo) }
    var hasCategoriesBeenLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.categories) {
        if (uiState.categories.isNotEmpty()) hasCategoriesBeenLoaded = true
    }

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(uiState.isTaskSaved) {
        if (uiState.isTaskSaved) {
            onNavigateBack()
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

        AddTaskScreen(
            uiState = uiState,
            snackbarHostState = snackbarHostState,
            taskParams = taskParams,
            categoryList = uiState.categories,
            onTitleChange = { title = it },
            onHourChange = { hour = it },
            onDateChange = { date = it },
            onPriorityChange = { priority = it },
            onCategoryChange = { category = it },
            onStatusChange = { status = it },
            onNavigateBack = onNavigateBack,
            onSaveTask = {
                if (isDataValid(title, hour, date))
                    viewModel.addTask(taskParams)
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
    }
}

fun isDataValid(
    title: String,
    hour: String,
    date: String,
): Boolean {
    return title.isNotEmpty() && hour.isNotEmpty() && date.isNotEmpty()
}