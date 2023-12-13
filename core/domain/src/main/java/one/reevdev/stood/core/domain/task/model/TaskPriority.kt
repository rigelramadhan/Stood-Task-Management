package one.reevdev.stood.core.domain.task.model

enum class TaskPriority(val priorityLevel: Int, val priorityLabel: String, val color: String) {
    Low(1, "Low", "#cccccc"),
    Normal(2, "Normal", "#ffcc00"),
    High(3, "High", "#ff3300"),
    Urgent(4, "Urgent", "#ff0000"),
    Critical(5, "Critical", "#cc0000"),
}