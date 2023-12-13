package one.reevdev.stood.core.data.datasource.local.task.helper

import one.reevdev.stood.core.data.datasource.local.task.model.CategoryEntity

object InitialDataSource {
    fun getCategories(): List<CategoryEntity> = listOf(
        CategoryEntity(0, "General", "#5BC8F4"),
        CategoryEntity(1, "Expenses", "#F4765B"),
        CategoryEntity(2, "Food", "#F4DB5B"),
        CategoryEntity(3, "Bills", "#AE8EEC"),
    )
}