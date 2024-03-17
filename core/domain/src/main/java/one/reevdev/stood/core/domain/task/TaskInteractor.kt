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
    override fun getTasks(): Flow<Result<List<Task>>> {
        taskRepository.getTasks().map {

        }
    }

    override fun getTaskById(id: String): Flow<Result<Task>> {
        TODO("Not yet implemented")
    }

    override fun getTaskByStatus(status: TaskStatus): Flow<Result<List<Task>>> {
        TODO("Not yet implemented")
    }

    override fun createTask(taskParams: TaskParams): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun updateTask(id: String, taskParams: TaskParams): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun deleteTask(id: String): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<Result<List<Category>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(category: Category): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun getCategoryById(id: String): Flow<Result<Category>> {
        TODO("Not yet implemented")
    }

}
