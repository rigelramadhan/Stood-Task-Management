package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.cosmoe.utils.resource.Resource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory

interface ITaskRepository {
    fun getTasks(): Flow<Resource<List<TaskWithCategory>>>
    fun getTaskById(id: String): Flow<Resource<TaskWithCategory>>
    fun getTaskByStatus(status: String): Flow<Resource<List<TaskWithCategory>>>
    fun createTask(taskParams: TaskEntityParams): Flow<Resource<String>>
    fun updateTask(id: String, taskParams: TaskEntityParams): Flow<Resource<String>>
    fun deleteTask(id: String): Flow<Resource<String>>

    fun getCategories(): Flow<Resource<List<CategoryEntity>>>
    fun createCategory(category: CategoryEntity): Flow<Resource<String>>
    fun getCategoryById(id: String): Flow<Resource<CategoryEntity>>
}