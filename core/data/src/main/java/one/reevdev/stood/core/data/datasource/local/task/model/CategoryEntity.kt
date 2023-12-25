package one.reevdev.stood.core.data.datasource.local.task.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("color")
    val color: String
)
