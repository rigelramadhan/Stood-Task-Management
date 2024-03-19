package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse

data class CategoryListResponse(

	@field:SerializedName("data")
	val data: List<CategoryResponse>
) : BaseStoodResponse()