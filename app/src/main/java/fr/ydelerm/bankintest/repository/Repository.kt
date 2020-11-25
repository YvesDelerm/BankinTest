package fr.ydelerm.bankintest.repository

import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.model.Category

interface Repository {
    fun refreshData()

    fun getCategories(): LiveData<List<Category>>
    fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>>
}