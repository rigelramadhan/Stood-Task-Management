package one.reevdev.stood.core.data.datasource.remote.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryListResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.CategoryResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskListResponse
import one.reevdev.stood.core.data.datasource.remote.task.model.TaskResponse
import one.reevdev.stood.core.data.datasource.remote.task.param.CategoryParam
import one.reevdev.stood.core.data.datasource.remote.task.param.TaskParam
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse
import javax.inject.Inject

class TaskRemoteDataSource @Inject constructor(
    private val apiService: TaskApiService
) : TaskApiService {
    override fun createTask(taskParam: TaskParam): Flow<BaseStoodResponse> {
        return apiService.createTask(taskParam)
    }

    override fun getTaskList(): Flow<TaskListResponse> {
        return apiService.getTaskList()
    }

    override fun getTaskById(id: String): Flow<TaskResponse> {
        return apiService.getTaskById(id)
    }

    override fun updateTask(id: String, taskParam: TaskParam): Flow<BaseStoodResponse> {
        return apiService.updateTask(id, taskParam)
    }

    override fun deleteTask(id: String): Flow<BaseStoodResponse> {
        return apiService.deleteTask(id)
    }

    override fun createCategory(categoryParam: CategoryParam): Flow<BaseStoodResponse> {
        TODO("Not yet implemented")
    }

    override fun getCategoryList(): Flow<CategoryListResponse> {
        return apiService.getCategoryList()
    }

    override fun getCategoryById(id: Int): Flow<CategoryResponse> {
        return apiService.getCategoryById(id)
    }
}