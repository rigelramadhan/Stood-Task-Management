package one.reevdev.stood.core.domain.task.model

enum class TaskStatus(val key: String, val label: String) {
    ToDo("ToDo", "To Do"), OnGoing("OnGoing", "On Going"), Done("Done", "Done")
}