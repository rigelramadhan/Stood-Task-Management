package one.reevdev.cosmoe.utils

import android.util.Log

object Logger {
    fun error(log: () -> String) {
        Log.e("Stood Logger", log())
    }
    fun error(log: Throwable) {
        Log.e("Stood Logger", log.message, log)
    }
}