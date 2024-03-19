package one.reevdev.stood.core.data.datasource.remote.task

import kotlinx.coroutines.flow.flow
import one.reevdev.stood.core.data.datasource.remote.task.param.CategoryParam
import one.reevdev.stood.core.data.datasource.remote.task.param.TaskParam
import javax.inject.Inject

class TaskRemoteDataSource @Inject constructor(
    private val apiService: TaskApiService
) {
    fun createTask(taskParam: TaskParam) = flow {
        emit(apiService.createTask(taskParam))
    }

    fun getTaskList() = flow {
        emit(apiService.getTaskList())
    }

    fun getTaskById(id: String) = flow {
        emit(apiService.getTaskById(id))
    }

    fun updateTask(id: String, taskParam: TaskParam) = flow {
        emit(apiService.updateTask(id, taskParam))
    }

    fun deleteTask(id: String) = flow {
        emit(apiService.deleteTask(id))
    }

    fun createCategory(categoryParam: CategoryParam) = flow {
        emit(apiService.createCategory(categoryParam))
    }

    fun getCategoryList() = flow {
        emit(apiService.getCategoryList())
    }

    fun getCategoryById(id: Int) = flow {
        emit(apiService.getCategoryById(id))
    }
}