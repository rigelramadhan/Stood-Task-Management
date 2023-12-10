package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDatastore @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
) : TaskRepository {
    override fun getTasks(): Flow<List<TaskEntity>> {
        return localDataSource.getTasks()
    }

    override fun getTaskById(id: String): Flow<TaskEntity> {
        return localDataSource.getTaskById(id)
    }

    override suspend fun createTask(title: String, priority: Int, time: String) {
        localDataSource.createTask(title, priority, time)
    }

    override suspend fun updateTask(id: String, title: String, priority: Int, time: String) {
        localDataSource.updateTask(id, title, priority, time)
    }

    override suspend fun deleteTask(id: String) {
        localDataSource.deleteTask(id)
    }
}