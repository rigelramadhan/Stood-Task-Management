package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import one.reevdev.stood.core.data.datasource.remote.task.TaskRemoteDataSource
import one.reevdev.stood.core.data.utils.Resource
import one.reevdev.stood.core.data.utils.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource,
) : ITaskRepository {
    override fun getTasks(): Flow<Resource<List<TaskWithCategory>>> = networkBoundResource(
        query = {
            localDataSource.getTasks()
        },
        fetch = {
            remoteDataSource.getTaskList().map {
                it.data
            }
        },
        saveFetchResult = {

        },
    )

    override fun getTaskById(id: String): Flow<Resource<TaskWithCategory>> {
        TODO("Not yet implemented")
    }

    override fun getTaskByStatus(status: String): Flow<Resource<List<TaskWithCategory>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createTask(taskParams: TaskEntityParams) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(id: String, taskParams: TaskEntityParams) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: String) {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<Resource<List<CategoryEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override fun getCategoryById(id: String): Flow<Resource<CategoryEntity>> = networkBoundResource(
        query = {
            localDataSource.getCategoryById(id)
        },
        fetch = {
            remoteDataSource.getCategoryById(id.toIntOrNull().orDefault(0))
        },
        saveFetchResult = {
            localDataSource.insertCategory()
        }
    )
}