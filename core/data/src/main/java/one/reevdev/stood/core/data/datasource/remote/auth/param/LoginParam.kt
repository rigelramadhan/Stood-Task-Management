package one.reevdev.stood.core.data.datasource.remote.auth.param

import com.google.gson.annotations.SerializedName

data class LoginParam(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)
