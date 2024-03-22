package one.reevdev.stood.core.data.repository.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import one.reevdev.cosmoe.utils.orDefault
import one.reevdev.cosmoe.utils.resource.Resource
import one.reevdev.stood.core.data.datasource.local.task.TaskLocalDataSource
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import one.reevdev.stood.core.data.datasource.remote.task.TaskRemoteDataSource
import one.reevdev.stood.core.data.datasource.remote.task.param.CategoryParam
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
            getCategories()
            remoteDataSource.getTaskList().first().data
        },
        saveFetchResult = {
            localDataSource.insertTask(it.map { task -> task.toEntity() })
        },
    )

    override fun getTaskById(id: String): Flow<Resource<TaskWithCategory>> = networkBoundResource(
        query = {
            localDataSource.getTaskById(id)
        },
        fetch = {
            remoteDataSource.getTaskById(id).first()
        },
        saveFetchResult = { response ->
            localDataSource.insertTask(response.data.toEntity())
        }
    )

    override fun getTaskByStatus(status: String): Flow<Resource<List<TaskWithCategory>>> {
        return localDataSource.getTaskByStatus(status).map { Resource.Success(it) }
    }

    override fun createTask(taskParams: TaskEntityParams): Flow<Resource<String>> = flow {
        try {
            val request = remoteDataSource.createTask(taskParams.toRequestParams()).first()
            emit(Resource.Success(request.message?.body.orEmpty()))
        } catch (e: Exception) {
            emit(Resource.Error(e, e.message.toString()))
        }
    }

    override fun updateTask(id: String, taskParams: TaskEntityParams) = flow {
        localDataSource.updateTask(id, taskParams)
        try {
            val request = remoteDataSource.updateTask(id, taskParams.toRequestParams()).first()
            emit(Resource.Success(request.message?.body.orEmpty()))
        } catch (e: Exception) {
            emit(Resource.Error(e, e.message.toString()))
        }
    }

    override fun deleteTask(id: String) = flow {
        localDataSource.deleteTask(id)
        try {
            val request = remoteDataSource.deleteTask(id).first()
            emit(Resource.Success(request.message?.body.orEmpty()))
        } catch (e: Exception) {
            emit(Resource.Error(e, e.message.toString()))
        }
    }

    override fun getCategories(): Flow<Resource<List<CategoryEntity>>> = networkBoundResource(
        query = {
            localDataSource.getCategories()
        },
        fetch = {
            remoteDataSource.getCategoryList()
        },
        saveFetchResult = { response ->
            val data = response.first().data
            localDataSource.insertCategories(data.map { it.toEntity() })
        },
    )

    override fun createCategory(category: CategoryEntity) = flow {
        localDataSource.insertCategory(category)
        try {
            val request = remoteDataSource.createCategory(CategoryParam(category.name)).first()
            emit(Resource.Success(request.message?.body.orEmpty()))
        } catch (e: Exception) {
            emit(Resource.Error(e, e.message.toString()))
        }
    }

    override fun getCategoryById(id: String): Flow<Resource<CategoryEntity>> = networkBoundResource(
        query = {
            localDataSource.getCategoryById(id)
        },
        fetch = {
            remoteDataSource.getCategoryById(id.toIntOrNull().orDefault(0)).first()
        },
        saveFetchResult = {
            localDataSource.insertCategory(it.data.toEntity())
        }
    )
}