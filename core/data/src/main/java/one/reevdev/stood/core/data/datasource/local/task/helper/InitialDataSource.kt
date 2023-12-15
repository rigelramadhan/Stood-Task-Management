package one.reevdev.stood.core.data.datasource.local.task.helper

import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity

object InitialDataSource {
    fun getCategories(): List<CategoryEntity> = listOf(
        CategoryEntity("default_general", "General", "#5BC8F4"),
        CategoryEntity("default_expenses", "Expenses", "#F4765B"),
        CategoryEntity("default_food", "Food", "#F4DB5B"),
        CategoryEntity("default_bills", "Bills", "#AE8EEC"),
    )
}