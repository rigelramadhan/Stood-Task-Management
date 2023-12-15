package one.reevdev.stood.core.domain.task.params

import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.TaskStatus

data class TaskUiParams(
    val title: String,
    val time: String,
    val date: String,
    val priority: Int,
    val category: Category,
    val status: TaskStatus
)
