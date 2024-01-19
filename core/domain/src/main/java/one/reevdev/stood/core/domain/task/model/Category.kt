package one.reevdev.stood.core.domain.task.model

import one.reevdev.cosmoe.utils.emptyString

data class Category(
    val id: String = emptyString(),
    val name: String = emptyString(),
    val color: String = "#000000",
)
