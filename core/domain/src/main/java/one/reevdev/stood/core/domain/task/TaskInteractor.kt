package one.reevdev.stood.core.domain.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.cosmoe.utils.resource.Resource
import one.reevdev.cosmoe.utils.resource.mapFlowData
import one.reevdev.stood.core.data.repository.task.ITaskRepository
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.core.domain.task.model.Task
import one.reevdev.stood.core.domain.task.model.TaskParams
import one.reevdev.stood.core.domain.task.model.TaskStatus
import javax.inject.Inject

class TaskInteractor @Inject constructor(
    private val taskRepository: ITaskRepository
) : TaskUseCase {
    override fun getTasks(): Flow<Resource<List<Task>>> {
        return taskRepository.getTasks().mapFlowData { data ->
            data.map { it.toDomain() }
        }
    }

    override fun getTaskById(id: String): Flow<Resource<Task>> {
        return taskRepository.getTaskById(id).mapFlowData {
            it.toDomain()
        }
    }

    override fun getTaskByStatus(status: TaskStatus): Flow<Resource<List<Task>>> {
        return taskRepository.getTaskByStatus(status.key).mapFlowData { data ->
            data.map { it.toDomain() }
        }
    }

    override fun createTask(taskParams: TaskParams): Flow<Resource<String>> {
        return taskRepository.createTask(taskParams.toEntity())
    }

    override fun updateTask(id: String, taskParams: TaskParams): Flow<Resource<String>> {
        return taskRepository.updateTask(id, taskParams.toEntity())
    }

    override fun deleteTask(id: String): Flow<Resource<String>> {
        return taskRepository.deleteTask(id)
    }

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return taskRepository.getCategories().mapFlowData { data ->
            data.map { it.toDomain() }
        }
    }

    override suspend fun createCategory(category: Category): Flow<Resource<String>> {
        return taskRepository.createCategory(category.toEntity())
    }

    override fun getCategoryById(id: String): Flow<Resource<Category>> {
        return taskRepository.getCategoryById(id).mapFlowData { it.toDomain() }
    }
}
