package one.reevdev.stood.core.data.repository.task

import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskResponse

fun CategoryResponse?.toEntity() = CategoryEntity(
    id = this?.id.toString(),
    name = this?.name.orEmpty(),
    color = emptyString(),
)

fun TaskResponse?.toEntity() = TaskEntity(
    id = this?.id.toString(),
    title = this?.title.orEmpty(),
    priority = this?.priority.orDefault(2),
    time = this?.dueTime.orEmpty(),
    categoryId = this?.categoryId.toString(),
    status = this?.taskStatus.orEmpty()
)

fun TaskEntityParams.toRequestParams() = // TODO: Map