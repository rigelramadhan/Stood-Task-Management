package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity

interface TaskRepository {
    fun getTasks(): Flow<List<TaskEntity>>
    fun getTaskById(id: String): Flow<TaskEntity>
    suspend fun createTask(title: String, priority: Int, time: String)
    suspend fun updateTask(id: String, title: String, priority: Int, time: String)
    suspend fun deleteTask(id: String)
}