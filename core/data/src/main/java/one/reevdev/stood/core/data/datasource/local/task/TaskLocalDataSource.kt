package one.reevdev.stood.core.data.datasource.local.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
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
    suspend fun createTask(title: String, priority: Int, time: String, categoryId: Int) {
        taskDao.insertTask(TaskEntity(
            id = "${Calendar.getInstance().timeInMillis}task-$priority",
            title = title,
            priority = priority,
            time = time,
            categoryId = categoryId
        ))
    }
    suspend fun updateTask(id: String, title: String, priority: Int, time: String, categoryId: Int) {
        taskDao.updateTask(
            TaskEntity(
                id = id,
                title = title,
                priority = priority,
                time = time,
                categoryId = categoryId
            )
        )
    }
    suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }

    suspend fun insertCategory(category: CategoryEntity) {
        taskDao.insertCategory(category)
    }

    fun getCategories() = taskDao.getCategories()

    fun getCategoryById(id: Int) = taskDao.getCategoryById(id)
}