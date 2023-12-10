package one.reevdev.stood.features.task.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import one.reevdev.stood.features.task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    title: String = stringResource(R.string.title_select_time),
    onDismissRequest: () -> Unit,
    onTimeSelected: (String, String) -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }
    val timeState = rememberTimePickerState()

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(48.dp))
            .padding(48.dp),
    ) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            }
        ) {
            Surface(
                shape = RoundedCornerShape(32.dp),
                color = MaterialTheme.colorScheme.surface,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                        .padding(24.dp),
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    TimePicker(
                        state = timeState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text(stringResource(R.string.action_cancel))
                        }
                        TextButton(onClick = {
                            onTimeSelected(
                                timeState.hour.toString().padStart(2, '0'),
                                timeState.minute.toString().padStart(2, '0')
                            )
                            showDialog = false
                        }) {
                            Text(stringResource(R.string.action_ok))
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(showDialog) {
        if (!showDialog) {
            onDismissRequest()
        }
    }
}