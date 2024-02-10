package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.stood.core.data.repository.task.ITaskRepository
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams
import one.reevdev.stood.core.domain.task.model.TaskStatus
import javax.inject.Inject

class TaskInteractor @Inject constructor(
    private val taskRepository: ITaskRepository
) : TaskUseCase {
    override fun getTasks(): Flow<List<Task>> {
        return taskRepository.getTasks().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTaskById(id: String): Flow<Task> {
        return taskRepository.getTaskById(id).map { it.toDomain() }
    }

    override fun getTaskByStatus(status: TaskStatus): Flow<List<Task>> {
        return taskRepository.getTaskByStatus(status.key).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun createTask(
        taskParams: TaskParams
    ) {
        taskRepository.createTask(taskParams.toEntity())
    }

    override suspend fun updateTask(
        id: String,
        taskParams: TaskParams
    ) {
        taskRepository.updateTask(id, taskParams.toEntity())
    }

    override suspend fun deleteTask(id: String) {
        taskRepository.deleteTask(id)
    }

    override fun getCategories(): Flow<List<Category>> {
        return taskRepository.getCategories().map {
            it.map { category -> category.toDomain() }
        }
    }

    override suspend fun createCategory(category: Category) {
        taskRepository.createCategory(category.toEntity())
    }

    override fun getCategoryById(id: String): Flow<Category> {
        return taskRepository.getCategoryById(id).map { it.toDomain() }
    }
}
