package one.reevdev.stood.core.data.datasource.local.task

import androidx.room.Database
import androidx.room.RoomDatabase
import one.reevdev.stood.core.data.datasource.local.task.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}