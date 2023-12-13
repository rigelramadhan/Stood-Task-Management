package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
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

    override suspend fun createTask(title: String, priority: Int, time: String, categoryId: Int) {
        localDataSource.createTask(title, priority, time, categoryId)
    }

    override suspend fun updateTask(
        id: String,
        title: String,
        priority: Int,
        time: String,
        categoryId: Int
    ) {
        localDataSource.updateTask(id, title, priority, time, categoryId)
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

    override fun getCategoryById(id: Int): Flow<CategoryEntity> {
        return localDataSource.getCategoryById(id)
    }
}