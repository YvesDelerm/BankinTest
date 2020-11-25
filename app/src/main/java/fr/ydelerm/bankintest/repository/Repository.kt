package fr.ydelerm.bankintest.repository

import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.vo.Status

interface Repository {
    fun refreshData()

    fun getCategories(): LiveData<List<Category>>
    fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>>
    fun getRequestStatus(): LiveData<Status>
}