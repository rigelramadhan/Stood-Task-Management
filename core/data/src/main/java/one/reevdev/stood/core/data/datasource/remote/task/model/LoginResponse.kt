package one.reevdev.stood.core.data.datasource.remote.task.model

import com.google.gson.annotations.SerializedName
import one.reevdev.stood.core.data.datasource.remote.utils.BaseStoodResponse

data class LoginResponse(

    @field:SerializedName("data")
    val data: LoginData? = null,

) : BaseStoodResponse()

data class LoginData(

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
