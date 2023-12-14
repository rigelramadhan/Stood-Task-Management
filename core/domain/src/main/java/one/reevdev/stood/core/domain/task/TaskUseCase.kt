package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskTime

interface TaskUseCase {
    fun getTasks(): Flow<List<Task>>
    fun getTaskById(id: String): Flow<Task>
    suspend fun createTask(title: String, priority: TaskPriority, time: TaskTime, categoryId: String)
    suspend fun updateTask(id: String, title: String, priority: TaskPriority, time: TaskTime, categoryId: String)
    suspend fun deleteTask(id: String)

    fun getCategories(): Flow<List<Category>>
    suspend fun createCategory(category: Category)
    fun getCategoryById(id: String): Flow<Category>
}