package one.reevdev.stood.core.domain.task

import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TaskWithCategory.toDomain(): Task = Task(
    id = task.id,
    title = task.title,
    priority = TaskPriority.values().first { it.priorityLevel == task.priority },
    time = task.time.toTaskTime(),
    category = category.toDomain()
)

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    name = name,
    color = color
)

fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    color = color
)

fun String.toTaskTime(): TaskTime {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date: Date? = inputFormat.parse(this)

    val outputTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = date?.let { outputTimeFormat.format(it) }.orEmpty()

    val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    val formattedDate = date?.let { outputDateFormat.format(it) }.orEmpty()

    return TaskTime(
        this,
        formattedTime,
        formattedDate
    )
}

fun mapToApiString(hour: String, date: String): String {
    val inputFormat = SimpleDateFormat("dd MMMM yyyy'T'HH:mm:ss'Z'", Locale.US)
    val dateTimeString = "${date}T$hour:00Z"
    val dateFormat: Date? = inputFormat.parse(dateTimeString)

    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    return dateFormat?.let { outputFormat.format(it) }.orEmpty()
}