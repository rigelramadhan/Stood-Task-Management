package one.reevdev.stood.core.data.datasource.local.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity
import one.reevdev.stood.core.data.datasource.local.task.model.TaskWithCategory

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskWithCategory>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: String): Flow<TaskWithCategory>

    @Query("SELECT * FROM task WHERE status = :status")
    fun getTaskByStatus(status: String): Flow<List<TaskWithCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Update(entity = TaskEntity::class)
    suspend fun updateTask(taskEntity: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(categories: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryById(id: String): Flow<CategoryEntity>
}