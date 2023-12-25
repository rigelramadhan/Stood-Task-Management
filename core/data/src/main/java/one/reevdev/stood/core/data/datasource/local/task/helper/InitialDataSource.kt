package one.reevdev.stood.core.data.datasource.local.task.helper

import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity

object InitialDataSource {
    fun getCategories(): List<CategoryEntity> = listOf(
        CategoryEntity("default_general", "General", "#5BC8F4"),
        CategoryEntity("default_work", "Work", "#F4765B"),
        CategoryEntity("default_study", "Study", "#F4DB5B"),
        CategoryEntity("default_grocery", "Grocery", "#AE8EEC"),
    )
}