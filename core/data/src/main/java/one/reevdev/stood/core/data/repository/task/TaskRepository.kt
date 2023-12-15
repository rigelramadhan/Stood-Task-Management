package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory

interface TaskRepository {
    fun getTasks(): Flow<List<TaskWithCategory>>
    fun getTaskById(id: String): Flow<TaskWithCategory>
    suspend fun createTask(taskParams: TaskEntityParams)
    suspend fun updateTask(id: String, taskParams: TaskEntityParams)
    suspend fun deleteTask(id: String)

    fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun createCategory(category: CategoryEntity)
    fun getCategoryById(id: String): Flow<CategoryEntity>
}