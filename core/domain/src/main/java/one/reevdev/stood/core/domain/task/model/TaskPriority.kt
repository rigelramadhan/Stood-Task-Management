package one.reevdev.stood.core.domain.task.model

enum class TaskPriority(val priorityLevel: Int, val priorityLabel: String) {
    Low(1, "Low"),
    Normal(2, "Normal"),
    High(3, "High"),
    Urgent(4, "Urgent"),
    Critical(5, "Critical"),
}