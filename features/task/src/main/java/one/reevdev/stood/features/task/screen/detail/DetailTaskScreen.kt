package one.reevdev.stood.features.task.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.TaskToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTaskScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    snackbarHostState: SnackbarHostState,
    onDeleteTask: () -> Unit,
//    navigateToEdit: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TaskToolbar(
                title = uiState.task?.title.orEmpty(),
                onBackButtonClick = onNavigateBack,
                actions = mapOf(
                    Icons.Outlined.Delete to onDeleteTask
                )
            )
        }
    ) { innerPadding ->
        uiState.task?.let {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 80.dp)
                        .align(Alignment.TopStart)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = it.title,
                        readOnly = true,
                        label = { Text(text = stringResource(R.string.label_title)) },
                        onValueChange = {},
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(1f),
                            value = it.time.time,
                            readOnly = true,
                            label = { Text(text = stringResource(R.string.label_hour)) },
                            onValueChange = {},
                            shape = RoundedCornerShape(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(1f),
                            value = it.time.date,
                            readOnly = true,
                            label = { Text(text = stringResource(R.string.label_date)) },
                            onValueChange = {},
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = it.priority.priorityLabel,
                        readOnly = true,
                        label = { Text(text = stringResource(R.string.label_priority)) },
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}