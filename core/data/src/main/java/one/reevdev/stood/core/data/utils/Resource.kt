package one.reevdev.stood.core.data.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<Nothing>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}