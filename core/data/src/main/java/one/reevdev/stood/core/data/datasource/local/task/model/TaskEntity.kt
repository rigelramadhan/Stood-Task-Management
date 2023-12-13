package one.reevdev.stood.core.data.datasource.local.task.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

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
    @ColumnInfo("categoryId")
    val categoryId: Int,
)

data class TaskWithCategory(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)
