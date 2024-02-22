package one.reevdev.stood.core.data.datasource.remote.task

import one.reevdev.stood.core.data.datasource.remote.task.param.CategoryParam
import one.reevdev.stood.core.data.datasource.remote.task.param.TaskParam
import javax.inject.Inject

class TaskRemoteDataSource @Inject constructor(
    private val apiService: TaskApiService
) : TaskApiService {
    override fun createTask(taskParam: TaskParam) = apiService.createTask(taskParam)

    override fun getTaskList() = apiService.getTaskList()

    override fun getTaskById(id: String) = apiService.getTaskById(id)

    override fun updateTask(id: String, taskParam: TaskParam) = apiService.updateTask(id, taskParam)

    override fun deleteTask(id: String) = apiService.deleteTask(id)

    override fun createCategory(categoryParam: CategoryParam) = apiService.createCategory(categoryParam)

    override fun getCategoryList() = apiService.getCategoryList()

    override fun getCategoryById(id: Int) = apiService.getCategoryById(id)
}