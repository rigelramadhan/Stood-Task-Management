package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse

data class RegisterResponse(

	@field:SerializedName("data")
	val data: RegisterData? = null,

) : BaseStoodResponse()

data class RegisterData(

	@field:SerializedName("roleId")
	val roleId: Int? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)