package fr.ydelerm.bankintest.persistence

import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(db: AppDatabase) : LocalDataSource {

    private var categoryDAO = db.categoryDAO()

    override fun insertCategories(categories: List<Category>) {
        return categoryDAO.insertCategories(categories)
    }

    override fun getCategories(): LiveData<List<Category>> {
        return categoryDAO.getCategories()
    }

    override fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>> {
        return categoryDAO.getSubCategories(parentCategoryId)
    }
}