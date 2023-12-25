package one.reevdev.stood.core.data.datasource.local.task.model

data class TaskEntityParams(
    val title: String,
    val priority: Int,
    val time: String,
    val categoryId: String,
    val status: String
)