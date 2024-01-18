package one.reevdev.stood.features.task.screen.list

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskRouter(
    context: Context = LocalContext.current,
    viewModel: TaskViewModel = hiltViewModel(),
    onTaskClick: (id: String) -> Unit,
    navigateToAddTask: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.getTasks()
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(message = it, withDismissAction = true)
        }
    }

    TaskScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onStatusSelect = { viewModel.setFilter(it) },
        navigateToDetail = onTaskClick,
        navigateToAddTask = navigateToAddTask,
        onTaskUpdate = { viewModel.updateTask(it) }
    )
}