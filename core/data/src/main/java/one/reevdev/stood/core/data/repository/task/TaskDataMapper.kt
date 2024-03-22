package one.reevdev.stood.core.data.repository.task

import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryItem
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskItem
import one.reevdev.stood.core.data.datasource.remote.task.param.TaskParam

fun CategoryItem.toEntity() = CategoryEntity(
    id = id.toString(),
    name = name.orEmpty(),
    color = "#FFFFFF",
)

fun TaskItem.toEntity() = TaskEntity(
    id = id.toString(),
    title = title.orEmpty(),
    priority = priority.orDefault(2),
    time = dueTime.orEmpty(),
    categoryId = categoryId.toString(),
    status = taskStatus.orEmpty(),
    periodic = periodic.orEmpty()
)

fun TaskEntityParams.toRequestParams() = TaskParam(
    dueTime = time,
    periodic = periodic,
    priority = priority,
    title = title,
    categoryId = categoryId.toIntOrNull().orDefault(0),
    taskStatus = status
)