package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse

data class TaskResponse(

    @field:SerializedName("data")
    val data: TaskItem
) : BaseStoodResponse()

data class TaskItem(

    @field:SerializedName("updatedBy")
    val updatedBy: String? = null,

    @field:SerializedName("periodic")
    val periodic: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("priority")
    val priority: Int? = null,

    @field:SerializedName("dueTime")
    val dueTime: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("createdBy")
    val createdBy: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("categoryId")
    val categoryId: Int? = null,

    @field:SerializedName("taskStatus")
    val taskStatus: String? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)