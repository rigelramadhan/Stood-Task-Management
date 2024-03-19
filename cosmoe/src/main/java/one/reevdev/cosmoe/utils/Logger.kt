package one.reevdev.cosmoe.utils

import android.util.Log

object Logger {

    private const val TAG_DEBUG = "[DEBUG] Stood Logger"
    private const val TAG_ERROR = "[ERROR] Stood Logger"

    fun debug(log: () -> String) {
        Log.d(TAG_DEBUG, log())
    }

    fun error(log: () -> String) {
        Log.e(TAG_ERROR, log())
    }

    fun error(log: Throwable) {
        Log.e(TAG_ERROR, log.message, log)
    }
}