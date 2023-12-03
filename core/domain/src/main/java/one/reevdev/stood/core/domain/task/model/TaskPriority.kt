package one.reevdev.stood.core.domain.task.model

enum class TaskPriority(val priorityLevel: Int) {
    Low(1),
    Normal(2),
    High(3),
    Urgent(4),
    Critical(5),
}