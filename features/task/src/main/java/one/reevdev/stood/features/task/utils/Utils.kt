package one.reevdev.stood.features.task.utils

fun Int.mapToLabel(): String = when(this) {
    1 -> "Low"
    2 -> "Normal"
    3 -> "High"
    4 -> "Urgent"
    5 -> "Critical"
    else -> "Other"
}