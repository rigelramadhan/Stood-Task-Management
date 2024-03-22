package one.reevdev.stood.core.data.datasource.remote.utils

import com.google.gson.annotations.SerializedName

open class BaseStoodResponse(

    @field:SerializedName("metadata")
    val metadata: Metadata? = null,

    @field:SerializedName("message")
    val message: Message? = null
)
