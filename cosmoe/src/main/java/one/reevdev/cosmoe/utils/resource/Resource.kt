package one.reevdev.cosmoe.utils.resource

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Error<out T>(val throwable: Throwable, val message: String? = null) : Resource<T>()
}