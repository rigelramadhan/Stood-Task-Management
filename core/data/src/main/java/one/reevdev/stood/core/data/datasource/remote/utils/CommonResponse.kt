package one.reevdev.stood.core.data.datasource.remote.utils

import com.google.gson.annotations.SerializedName

data class Metadata(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("statusCode")
    val statusCode: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Message(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null
)