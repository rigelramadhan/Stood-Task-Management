package one.reevdev.stood.features.task.screen.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailTaskRouter(
    context: Context = LocalContext.current,
    viewModel: DetailTaskViewModel = hiltViewModel(),
    taskId: String,
    onNavigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(uiState.isTaskDeleted) {
        if (uiState.isTaskDeleted) {
            onNavigateBack()
        }
    }

    DetailTaskScreen(
        uiState = uiState,
        onDeleteTask = { viewModel.deleteTaskById(taskId) },
        onNavigateBack = onNavigateBack
    )
}