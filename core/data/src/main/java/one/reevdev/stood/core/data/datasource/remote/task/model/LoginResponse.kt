package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

    @field:SerializedName("data")
    val data: LoginData? = null,

    @field:SerializedName("message")
    val message: Message? = null
)

data class LoginData(

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
