package one.reevdev.stood.core.domain.task.model

data class Task(
    val id: String,
    val title: String,
    val priority: TaskPriority,
    val time: TaskTime,
    val category: Category,
    val status: TaskStatus,
    val periodic: TaskPeriodic,
)
data class TaskTime(
    val fullISOFormat: String,
    val time: String,
    val date: String,
)