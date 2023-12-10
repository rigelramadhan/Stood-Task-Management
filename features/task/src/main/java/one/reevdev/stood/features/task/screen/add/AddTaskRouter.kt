package one.reevdev.stood.features.task.screen.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AddTaskRouter(
    viewModel: AddTaskViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
            viewModel.addTask(
                title, hour, date, priority
            )
        }
    )
}