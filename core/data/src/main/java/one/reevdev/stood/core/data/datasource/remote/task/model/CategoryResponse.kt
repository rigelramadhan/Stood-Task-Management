package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse

data class CategoryResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null,
) : BaseStoodResponse()