package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.cosmoe.utils.resource.Resource
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams
import one.reevdev.stood.core.domain.task.model.TaskStatus

interface TaskUseCase {
    fun getTasks(): Flow<Resource<List<Task>>>
    fun getTaskById(id: String): Flow<Resource<Task>>
    fun getTaskByStatus(status: TaskStatus): Flow<Resource<List<Task>>>
    fun createTask(taskParams: TaskParams): Flow<Resource<String>>

    fun updateTask(id: String, taskParams: TaskParams): Flow<Resource<String>>

    fun deleteTask(id: String): Flow<Resource<String>>

    fun getCategories(): Flow<Resource<List<Category>>>
    suspend fun createCategory(category: Category): Flow<Resource<String>>
    fun getCategoryById(id: String): Flow<Resource<Category>>
}