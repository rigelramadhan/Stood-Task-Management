package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.cosmoe.utils.toList
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import one.reevdev.stood.core.data.datasource.remote.task.TaskRemoteDataSource
import one.reevdev.stood.core.data.datasource.remote.task.param.CategoryParam
import one.reevdev.stood.core.data.utils.Resource
import one.reevdev.stood.core.data.utils.networkBoundResource
import java.util.Calendar
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
            getCategories()
            remoteDataSource.getTaskList().map {
                it.data
            }
        },
        saveFetchResult = {
            localDataSource.insertTask(it.firstOrNull()?.map { task -> task.toEntity() }
                .orDefault(emptyList()))
        },
    )

    override fun getTaskById(id: String): Flow<Resource<TaskWithCategory>> = networkBoundResource(
        query = {
            localDataSource.getTaskById(id)
        },
        fetch = {
            remoteDataSource.getTaskById(id)
        },
        saveFetchResult = { response ->
            localDataSource.insertTask(response.map { it.toEntity() }.first().toList())
        }
    )

    override fun getTaskByStatus(status: String): Flow<Resource<List<TaskWithCategory>>> {
        return localDataSource.getTaskByStatus(status).map { Resource.Success(it) }
    }

    override suspend fun createTask(taskParams: TaskEntityParams) {
        with(taskParams) {
            val entity = TaskEntity(
                id = "${Calendar.getInstance().timeInMillis}task-$priority",
                title = title,
                priority = priority,
                time = time,
                categoryId = categoryId,
                status = status,
                periodic = periodic
            )
            localDataSource.insertTask(
                entity
            )
            remoteDataSource.createTask(taskParams.toRequestParams())
        }
    }

    override suspend fun updateTask(id: String, taskParams: TaskEntityParams) {
        localDataSource.updateTask(id, taskParams)
        remoteDataSource.updateTask(id, taskParams.toRequestParams())
    }

    override suspend fun deleteTask(id: String) {
        localDataSource.deleteTask(id)
        remoteDataSource.deleteTask(id)
    }

    override fun getCategories(): Flow<Resource<List<CategoryEntity>>> = networkBoundResource(
        query = {
            localDataSource.getCategories()
        },
        fetch = {
            remoteDataSource.getCategoryList()
        },
        saveFetchResult = { response ->
            localDataSource.insertCategories(response.map { list -> list.data.map { it.toEntity() } }
                .firstOrNull().orEmpty())
        },
    )

    override suspend fun createCategory(category: CategoryEntity) {
        localDataSource.insertCategory(category)
        remoteDataSource.createCategory(
            CategoryParam(category.name)
        )
    }

    override fun getCategoryById(id: String): Flow<Resource<CategoryEntity>> = networkBoundResource(
        query = {
            localDataSource.getCategoryById(id)
        },
        fetch = {
            remoteDataSource.getCategoryById(id.toIntOrNull().orDefault(0))
        },
        saveFetchResult = {
            localDataSource.insertCategory(it.first().toEntity())
        }
    )
}