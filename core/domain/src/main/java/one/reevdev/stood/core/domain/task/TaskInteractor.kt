package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.stood.core.data.repository.task.TaskRepository
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskPriority
import one.reevdev.stood.core.domain.task.model.TaskSort
import one.reevdev.stood.core.domain.task.model.TaskTime
import javax.inject.Inject

class TaskInteractor @Inject constructor(
    private val taskRepository: TaskRepository
) : TaskUseCase {
    override fun getTasks(): Flow<List<Task>> {
        return taskRepository.getTasks().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTaskSorted(sort: TaskSort): Flow<List<Task>> {
        return taskRepository.getTaskSorted(sort.sortQuery).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTaskById(id: String): Flow<Task> {
        return taskRepository.getTaskById(id).map { it.toDomain() }
    }

    override suspend fun createTask(title: String, priority: TaskPriority, time: TaskTime) {
        taskRepository.createTask(title, priority.priorityLevel, time.fullISOFormat)
    }

    override suspend fun updateTask(id: String, title: String, priority: TaskPriority, time: TaskTime) {
        taskRepository.updateTask(id, title, priority.priorityLevel, time.fullISOFormat)
    }

    override suspend fun deleteTask(id: String) {
        taskRepository.deleteTask(id)
    }
}
