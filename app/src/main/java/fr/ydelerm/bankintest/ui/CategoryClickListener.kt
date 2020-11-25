package fr.ydelerm.bankintest.ui

import fr.ydelerm.bankintest.model.Category

interface CategoryClickListener {
    fun onCategoryClicked(category: Category)
}