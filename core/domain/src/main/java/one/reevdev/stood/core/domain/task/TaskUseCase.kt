package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams
import one.reevdev.stood.core.domain.task.model.TaskStatus

interface TaskUseCase {
    fun getTasks(): Flow<Result<List<Task>>>
    fun getTaskById(id: String): Flow<Result<Task>>
    fun getTaskByStatus(status: TaskStatus): Flow<Result<List<Task>>>
    fun createTask(taskParams: TaskParams): Flow<Result<String>>

    fun updateTask(id: String, taskParams: TaskParams): Flow<Result<String>>

    fun deleteTask(id: String): Flow<Result<String>>

    fun getCategories(): Flow<Result<List<Category>>>
    suspend fun createCategory(category: Category): Flow<Result<String>>
    fun getCategoryById(id: String): Flow<Result<Category>>
}