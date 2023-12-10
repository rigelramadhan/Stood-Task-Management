package one.reevdev.stood.features.task.screen.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.TaskToolbar

@OptIn(ExperimentalMaterial3Api::class)
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
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = hour,
                        label = { Text(text = stringResource(R.string.label_hour)) },
                        onValueChange = onHourChange,
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = date,
                        label = { Text(text = stringResource(R.string.label_date)) },
                        onValueChange = onDateChange,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDropdownExpanded = true },
                    enabled = false,
                    readOnly = true,
                    interactionSource = interactionSource,
                    value = priorityLabel,
                    label = { Text(text = stringResource(R.string.label_title)) },
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(16.dp)
                )
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
        }
    }
}