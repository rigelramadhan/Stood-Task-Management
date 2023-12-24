package one.reevdev.stood.features.task.screen.detail

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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.params.TaskUiParams
import one.reevdev.stood.features.task.R
import one.reevdev.stood.features.task.component.category.CategoryDropdown
import one.reevdev.stood.features.task.component.priority.PriorityButton
import one.reevdev.stood.features.task.component.priority.PriorityPickerBottomSheet
import one.reevdev.stood.features.task.component.status.StatusFilterSelector
import one.reevdev.stood.features.task.component.task.TaskToolbar
import one.reevdev.stood.features.task.screen.add.Divider
import one.reevdev.stood.features.task.screen.add.TimeAndDateDialog
import one.reevdev.stood.features.task.utils.actions.TaskAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTaskScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    snackbarHostState: SnackbarHostState,
    taskParams: TaskUiParams,
    categoryList: List<Category>,
    taskAction: TaskAction,
    onDeleteTask: () -> Unit,
//    navigateToEdit: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val priorityPickerBottomSheetState = rememberModalBottomSheetState()
    var showPriorityPickerBottomSheet by remember { mutableStateOf(false) }
    var isCategoryDropdownExpanded by remember { mutableStateOf(false) }
    var isDatePickerDialogShown by remember { mutableStateOf(false) }
    var isTimePickerDialogShown by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

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
                StatusFilterSelector(
                    title = stringResource(R.string.label_status),
                    modifier = Modifier.fillMaxWidth(),
                    isSelectedStatus = { taskParams.status == it },
                    onStatusSelect = taskAction.onStatusChange
                )
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = taskParams.title,
                    label = { Text(text = stringResource(R.string.label_title)) },
                    onValueChange = taskAction.onTitleChange,
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
                            text = taskParams.time.ifEmpty { stringResource(R.string.action_pick_a_time) },
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
                            text = taskParams.date.ifEmpty { stringResource(R.string.action_pick_a_date) },
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                PriorityButton(
                    onClick = { showPriorityPickerBottomSheet = true },
                    priority = taskParams.priority.priorityLabel,
                    color = TaskPriority.values()
                        .first { it == taskParams.priority }.color
                )
                Divider(modifier = Modifier.padding(top = 8.dp))
                Text(text = stringResource(R.string.label_category))
                Spacer(modifier = Modifier.height(8.dp))
                CategoryDropdown(
                    category = taskParams.category,
                    isDropdownExpanded = isCategoryDropdownExpanded,
                    onDropdownClick = {
                        isCategoryDropdownExpanded = !isCategoryDropdownExpanded
                    }
                )
                DropdownMenu(
                    expanded = isCategoryDropdownExpanded,
                    onDismissRequest = { isCategoryDropdownExpanded = false }) {
                    categoryList.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name) },
                            onClick = {
                                taskAction.onCategoryChange(category)
                                isCategoryDropdownExpanded = false
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
                onClick = taskAction.onSaveTask
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
                onTimeChange = taskAction.onHourChange,
                onDateChange = taskAction.onDateChange,
                onTimeDismiss = { isTimePickerDialogShown = false },
                onDateDismiss = { isDatePickerDialogShown = false }
            )
            if (showPriorityPickerBottomSheet)
                PriorityPickerBottomSheet(
                    onDismissRequest = {
                        showPriorityPickerBottomSheet = false
                    },
                    title = stringResource(R.string.label_choose_the_priority),
                    sheetState = priorityPickerBottomSheetState,
                    priorities = TaskPriority.values().toList(),
                    onPriorityClick = {
                        scope.launch {
                            priorityPickerBottomSheetState.hide()
                        }.invokeOnCompletion {
                            showPriorityPickerBottomSheet = false
                        }
                        taskAction.onPriorityChange(it)
                    }
                )
        }
    }
}