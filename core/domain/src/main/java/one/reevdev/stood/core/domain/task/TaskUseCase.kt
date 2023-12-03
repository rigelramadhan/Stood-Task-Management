package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskTime

interface TaskUseCase {
    fun getTasks(): Flow<List<Task>>
    fun getTaskById(id: String): Flow<Task>
    fun createTask(title: String, priority: TaskPriority, time: TaskTime)
    fun updateTask(title: String, priority: TaskPriority, time: TaskTime)
    fun deleteTask(id: String)
}