package one.reevdev.stood.core.domain.task.model

enum class TaskPriority(val priorityLevel: Int, val priorityLabel: String, val color: String) {
    Low(1, "Low", "#72A082"),
    Normal(2, "Normal", "#FFCC00"),
    High(3, "High", "#DC143C"),
    Urgent(4, "Urgent", "#A020F0"),
    Critical(5, "Critical", "#FF00FF"),
}