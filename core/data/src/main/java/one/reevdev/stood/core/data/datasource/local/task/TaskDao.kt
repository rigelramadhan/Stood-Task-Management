package one.reevdev.stood.core.data.datasource.local.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: String): Flow<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Update(entity = TaskEntity::class)
    suspend fun updateTask(taskEntity: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: String)
}