package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDatastore @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
) : TaskRepository {
    override fun getTasks(): Flow<List<TaskWithCategory>> {
        return localDataSource.getTasks()
    }

    override fun getTaskById(id: String): Flow<TaskWithCategory> {
        return localDataSource.getTaskById(id)
    }

    override fun getTaskByStatus(status: String): Flow<List<TaskWithCategory>> {
        return localDataSource.getTaskByStatus(status)
    }

    override suspend fun createTask(taskParams: TaskEntityParams) {
        localDataSource.createTask(taskParams)
    }

    override suspend fun updateTask(
        id: String,
        taskParams: TaskEntityParams
    ) {
        localDataSource.updateTask(id, taskParams)
    }

    override suspend fun deleteTask(id: String) {
        localDataSource.deleteTask(id)
    }

    override fun getCategories(): Flow<List<CategoryEntity>> {
        return localDataSource.getCategories()
    }

    override suspend fun createCategory(category: CategoryEntity) {
        localDataSource.insertCategory(category)
    }

    override fun getCategoryById(id: String): Flow<CategoryEntity> {
        return localDataSource.getCategoryById(id)
    }
}