package fr.ydelerm.bankintest

import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.model.Parent

class TestDataHelper {
    fun mainCategories(): List<Category>? {
        return arrayListOf(
            Category(
                1, "res/1", "Category", "Main 1", null,
                custom = false,
                other = false,
                isDeleted = false
            ),
            Category(
                2, "res/2", "Category", "Main 2", null,
                custom = false,
                other = false,
                isDeleted = false
            )
        )
    }

    fun subCategories(): List<Category>? {
        val parent1 = Parent(1, "res/1", "Category")
        val parent2 = Parent(2, "res/2", "Category")
        return arrayListOf(
            Category(
                11, "res/11", "Category", "Sub 1 1", parent1,
                custom = false,
                other = false,
                isDeleted = false
            ),
            Category(
                12, "res/12", "Category", "Sub 1 2", parent1,
                custom = false,
                other = false,
                isDeleted = false
            ),
            Category(
                13, "res/13", "Category", "Sub 1 3", parent1,
                custom = false,
                other = false,
                isDeleted = false
            ),
            Category(
                21, "res/21", "Category", "Sub 2 1", parent2,
                custom = false,
                other = false,
                isDeleted = false
            )
        )
    }

    fun categories(): List<Category>? {
        val categories = mainCategories()?.toMutableList()
        subCategories()?.let { categories?.addAll(it) }
        return categories
    }
}