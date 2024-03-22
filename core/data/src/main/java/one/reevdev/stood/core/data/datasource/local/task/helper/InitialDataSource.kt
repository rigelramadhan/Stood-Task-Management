package one.reevdev.stood.core.data.datasource.local.task.helper

import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity

object InitialDataSource {
    fun getCategories(): List<CategoryEntity> = listOf(
        CategoryEntity("999999", "Other", "#5BC8F4"),
    )
}