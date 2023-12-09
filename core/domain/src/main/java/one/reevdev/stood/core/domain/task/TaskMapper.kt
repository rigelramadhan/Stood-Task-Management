package one.reevdev.stood.core.domain.task

import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TaskEntity.toDomain(): Task = Task(
    id = id,
    title = title,
    priority = TaskPriority.values().first { it.priorityLevel == priority },
    time = time.toTaskTime()
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
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val dateTimeString = "${date}T${hour}:00Z"
    val outputDate: Date? = outputFormat.parse(dateTimeString)

    return outputDate?.let { outputFormat.format(it) }.orEmpty()
}