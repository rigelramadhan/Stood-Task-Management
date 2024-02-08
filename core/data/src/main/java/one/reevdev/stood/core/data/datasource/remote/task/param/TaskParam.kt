package one.reevdev.stood.core.data.datasource.remote.task.param

import com.google.gson.annotations.SerializedName

data class TaskParam(

	@field:SerializedName("due_time")
	val dueTime: String,

	@field:SerializedName("periodic")
	val periodic: String,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("categoryId")
	val categoryId: Int,

	@field:SerializedName("taskStatus")
	val taskStatus: String
)
