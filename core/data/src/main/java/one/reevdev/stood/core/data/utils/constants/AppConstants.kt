package one.reevdev.stood.core.data.utils.constants

object AppConstants {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiUrl(): String
    external fun getEncryptionKey(): String
}