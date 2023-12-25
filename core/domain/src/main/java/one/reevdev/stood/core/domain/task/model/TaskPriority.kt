package one.reevdev.stood.core.domain.task.model

enum class TaskPriority(val priorityLevel: Int, val priorityLabel: String, val color: String) {
    Low(5, "Low", "#72A082"),
    Normal(4, "Normal", "#FFCC00"),
    High(3, "High", "#DC143C"),
    Urgent(2, "Urgent", "#A020F0"),
    Critical(1, "Critical", "#FF00FF"),
}