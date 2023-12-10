package one.reevdev.stood.features.task.screen.add

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.TaskDatePickerDialog
import one.reevdev.stood.features.task.component.TaskToolbar
import one.reevdev.stood.features.task.component.TimePickerDialog

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    uiState: AddTaskUiState,
    taskPriorityList: Array<String> = stringArrayResource(id = R.array.task_priorities),
    title: String,
    hour: String,
    date: String,
    priority: Int,
    onTitleChange: (String) -> Unit,
    onHourChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onPriorityChange: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    onSaveTask: () -> Unit,
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val priorityLabel = when (priority) {
        1 -> taskPriorityList[0]
        2 -> taskPriorityList[1]
        3 -> taskPriorityList[2]
        4 -> taskPriorityList[3]
        5 -> taskPriorityList[4]
        else -> "Other"
    }
    var isDatePickerDialogShown by remember { mutableStateOf(false) }
    var isTimePickerDialogShown by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TaskToolbar(
                title = stringResource(R.string.title_add_task),
                onBackButtonClick = onNavigateBack,
                actions = mapOf()
            )
        }
    ) { innerPadding ->
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
                    value = title,
                    label = { Text(text = stringResource(R.string.label_title)) },
                    onValueChange = onTitleChange,
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { isTimePickerDialogShown = true },
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = hour.ifEmpty { stringResource(R.string.action_pick_a_time) },
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { isDatePickerDialogShown = true },
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = date.ifEmpty { stringResource(R.string.action_pick_a_date) },
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { isDropdownExpanded = true },
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = "Priority - $priorityLabel",
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = if (isDropdownExpanded)
                            Icons.Outlined.KeyboardArrowUp else
                            Icons.Outlined.KeyboardArrowDown,
                        contentDescription = stringResource(
                            R.string.content_description_dropdown_icon
                        )
                    )
                }
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false }) {
                    taskPriorityList.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                onPriorityChange(index.plus(1))
                                isDropdownExpanded = false
                            }
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .align(Alignment.BottomCenter),
                onClick = onSaveTask
            ) {
                Text(text = stringResource(R.string.action_save_task))
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
            TimeAndDateDialog(
                isTimeDialogShown = isTimePickerDialogShown,
                isDateDialogShown = isDatePickerDialogShown,
                onTimeChange = onHourChange,
                onDateChange = onDateChange,
                onTimeDismiss = { isTimePickerDialogShown = false },
                onDateDismiss = { isDatePickerDialogShown = false }
            )
        }
    }
}

@Composable
fun TimeAndDateDialog(
    modifier: Modifier = Modifier,
    isTimeDialogShown: Boolean,
    isDateDialogShown: Boolean,
    onTimeChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onTimeDismiss: () -> Unit,
    onDateDismiss: () -> Unit
) {
    if (isTimeDialogShown) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TimePickerDialog(
                onDismissRequest = onTimeDismiss,
                onTimeSelected = { hour, minute ->
                    onTimeChange("$hour:$minute")
                }
            )
        }
    }
    if (isDateDialogShown) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TaskDatePickerDialog(
                onDateSelected = {
                    onDateChange(it)
                },
                onDismiss = onDateDismiss
            )
        }
    }
}