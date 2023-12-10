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

@Composable
fun AddTaskRouter(
    viewModel: AddTaskViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    var title by rememberSaveable { mutableStateOf("") }
    var hour by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var priority by rememberSaveable { mutableStateOf(2) }

    LaunchedEffect(uiState.isTaskSaved) {
        if (uiState.isTaskSaved) {
            onNavigateBack()
        }
    }

    AddTaskScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        title = title,
        hour = hour,
        date = date,
        priority = priority,
        onTitleChange = { title = it },
        onHourChange = { hour = it },
        onDateChange = { date = it },
        onPriorityChange = { priority = it },
        onNavigateBack = onNavigateBack,
        onSaveTask = {
            if (isDataValid(title, hour, date))
                viewModel.addTask(
                    title, hour, date, priority
                )
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

fun isDataValid(
    title: String,
    hour: String,
    date: String,
): Boolean {
    return title.isNotEmpty() && hour.isNotEmpty() && date.isNotEmpty()
}