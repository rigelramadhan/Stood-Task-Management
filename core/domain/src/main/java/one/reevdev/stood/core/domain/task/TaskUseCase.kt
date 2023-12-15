package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams

interface TaskUseCase {
    fun getTasks(): Flow<List<Task>>
    fun getTaskById(id: String): Flow<Task>
    suspend fun createTask(
        taskParams: TaskParams
    )

    suspend fun updateTask(
        id: String,
        taskParams: TaskParams
    )

    suspend fun deleteTask(id: String)

    fun getCategories(): Flow<List<Category>>
    suspend fun createCategory(category: Category)
    fun getCategoryById(id: String): Flow<Category>
}