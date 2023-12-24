package one.reevdev.stood.features.task.utils.actions

import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskStatus

data class TaskAction(
    val onTitleChange: (String) -> Unit,
    val onHourChange: (String) -> Unit,
    val onDateChange: (String) -> Unit,
    val onPriorityChange: (TaskPriority) -> Unit,
    val onCategoryChange: (Category) -> Unit,
    val onStatusChange: (TaskStatus) -> Unit,
    val onSaveTask: () -> Unit,
)
