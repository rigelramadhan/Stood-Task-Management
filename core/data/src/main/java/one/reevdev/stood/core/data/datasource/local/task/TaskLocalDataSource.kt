package one.reevdev.stood.core.data.datasource.local.task

import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import java.util.Calendar
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(private val taskDao: TaskDao) {
    fun getTasks(): Flow<List<TaskEntity>> {
        return taskDao.getTasks()
    }
    fun getTaskById(id: String): Flow<TaskEntity> {
        return taskDao.getTaskById(id)
    }
    suspend fun createTask(title: String, priority: Int, time: String) {
        taskDao.insertTask(TaskEntity(
            id = "${Calendar.getInstance().timeInMillis}task-$priority",
            title = title,
            priority = priority,
            time = time
        ))
    }
    suspend fun updateTask(id: String, title: String, priority: Int, time: String) {
        taskDao.updateTask(
            TaskEntity(
                id,
                title,
                priority,
                time
            )
        )
    }
    suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }
}