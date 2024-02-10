package one.reevdev.stood.core.data.utils

object AppConstants {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiUrl(): String
}