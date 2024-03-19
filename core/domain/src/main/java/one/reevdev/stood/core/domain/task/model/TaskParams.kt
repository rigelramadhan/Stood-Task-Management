package one.reevdev.stood.core.domain.task.model

data class TaskParams(
    val title: String,
    val priority: TaskPriority,
    val time: TaskTime,
    val categoryId: String,
    val status: TaskStatus,
    val periodic: TaskPeriodic,
)
