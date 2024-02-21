package one.reevdev.stood.core.data.datasource.local.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntityParams
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory
import java.util.Calendar
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(private val taskDao: TaskDao) {
    fun getTasks(): Flow<List<TaskWithCategory>> {
        return taskDao.getTasks()
    }
    fun getTaskById(id: String): Flow<TaskWithCategory> {
        return taskDao.getTaskById(id)
    }
    fun getTaskByStatus(status: String): Flow<List<TaskWithCategory>> {
        return taskDao.getTaskByStatus(status)
    }
    suspend fun createTask(taskParams: TaskEntityParams) {
        with(taskParams) {
            taskDao.insertTask(
                TaskEntity(
                    id = "${Calendar.getInstance().timeInMillis}task-$priority",
                    title = title,
                    priority = priority,
                    time = time,
                    categoryId = categoryId,
                    status = status
                )
            )
        }
    }

    suspend fun insertTask(taskEntity: TaskEntity) {
        taskDao.insertTask(taskEntity)
    }

    suspend fun insertTask(taskEntity: List<TaskEntity>) {
        taskDao.insertTask(taskEntity)
    }

    suspend fun updateTask(id: String, taskParams: TaskEntityParams) {
        with(taskParams) {
            taskDao.updateTask(
                TaskEntity(
                    id = id,
                    title = title,
                    priority = priority,
                    time = time,
                    categoryId = categoryId,
                    status = status
                )
            )
        }
    }
    suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }

    suspend fun insertCategory(category: CategoryEntity) {
        taskDao.insertCategory(category)
    }

    suspend fun insertCategories(category: List<CategoryEntity>) {
        taskDao.insertCategories(category)
    }

    fun getCategories() = taskDao.getCategories()

    fun getCategoryById(id: String) = taskDao.getCategoryById(id)
}