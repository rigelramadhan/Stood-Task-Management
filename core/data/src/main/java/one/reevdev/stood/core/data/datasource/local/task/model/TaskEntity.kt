package one.reevdev.stood.core.data.datasource.local.task.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("priority")
    val priority: Int,
    @ColumnInfo("time")
    val time: String,
)
