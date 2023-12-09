package one.reevdev.stood.features.task.screen.add

import androidx.compose.runtime.Composable
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

    var titleState by rememberSaveable { mutableStateOf("") }
    var hourState by rememberSaveable { mutableStateOf("") }
    var dateState by rememberSaveable { mutableStateOf("") }
    var priorityState by rememberSaveable { mutableStateOf(2) }

    AddTaskScreen(
        uiState = uiState,
        title = titleState,
        hour = hourState,
        date = dateState,
        priority = priorityState,
        onTitleChange = { titleState = it },
        onHourChange = { hourState = it },
        onDateChange = { dateState = it },
        onPriorityChange = { priorityState = it },
        onNavigateBack = onNavigateBack,
        onSaveTask = { title, hour, date, priority ->
            viewModel.addTask(
                title, hour, date, priority
            )
        }
    )
}