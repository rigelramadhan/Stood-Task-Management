package one.reevdev.stood.features.task.screen.list

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskRouter(
    context: Context = LocalContext.current,
    viewModel: TaskViewModel = hiltViewModel(),
    onTaskClick: (id: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }
    }

    TaskScreen(
        uiState = uiState,
        navigateToDetail = onTaskClick
    )
}