package one.reevdev.cosmoe.utils

fun <T> T?.orDefault(defaultValue: T) = this ?: defaultValue

fun <T> T?.isNull() = this == null

fun <T> T?.isNotNull() = this != null

fun <T> T?.toList() = this?.let { listOf<T>(it) } ?: emptyList()