package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskSort
import one.reevdev.stood.core.domain.task.model.TaskTime

interface TaskUseCase {
    fun getTasks(): Flow<List<Task>>
    fun getTaskSorted(sort: TaskSort): Flow<List<Task>>
    fun getTaskById(id: String): Flow<Task>
    suspend fun createTask(title: String, priority: TaskPriority, time: TaskTime)
    suspend fun updateTask(id: String, title: String, priority: TaskPriority, time: TaskTime)
    suspend fun deleteTask(id: String)
}