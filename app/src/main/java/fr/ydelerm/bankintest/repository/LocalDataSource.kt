package fr.ydelerm.bankintest.repository

import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.model.Category

interface LocalDataSource {
    fun insertCategories(categories: List<Category>)

    fun getCategories(): LiveData<List<Category>>

    fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>>
}