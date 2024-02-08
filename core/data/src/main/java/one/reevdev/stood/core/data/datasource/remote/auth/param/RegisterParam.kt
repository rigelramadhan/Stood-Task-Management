package one.reevdev.stood.core.data.datasource.remote.auth.param

import com.google.gson.annotations.SerializedName

data class RegisterParam(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("displayName")
	val displayName: String,

	@field:SerializedName("roleId")
	val roleId: String,

	@field:SerializedName("confirmPassword")
	val confirmPassword: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
