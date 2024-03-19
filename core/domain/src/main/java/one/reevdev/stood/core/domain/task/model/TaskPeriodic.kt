package one.reevdev.stood.core.domain.task.model

enum class TaskPeriodic(val type: String) {
    None("None"), Daily("Daily"), Weekly("Weekly"), Monthly("Monthly"), Yearly("Yearly")
}
