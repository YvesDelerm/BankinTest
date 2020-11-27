package fr.ydelerm.bankintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.repository.LocalDataSource

class FakeLocalDataSource(
    private val futureCategories: List<Category>?,
    private val futureSubCategories: List<Category>?
) : LocalDataSource {
    private var insertedCategories = ArrayList<Category>()
    private val categories = MutableLiveData<List<Category>>()
    private val subcategories = MutableLiveData<List<Category>>()

    override fun insertCategories(categoriesToAdd: List<Category>) {
        insertedCategories.addAll(categoriesToAdd)
        categories.value = futureCategories
        subcategories.value = futureSubCategories
    }

    override fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    override fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>> {
        return subcategories
    }

    fun getInsertedCategories(): List<Category> {
        return insertedCategories
    }

}
