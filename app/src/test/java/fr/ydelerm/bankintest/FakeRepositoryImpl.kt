package fr.ydelerm.bankintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.repository.Repository
import fr.ydelerm.bankintest.vo.Status

class FakeRepositoryImpl(private val futureCategories: List<Category>?, private val futureSubCategories: List<Category>?) : Repository {
    private val status = MutableLiveData<Status>()
    private val categories = MutableLiveData<List<Category>>()
    private val subcategories = MutableLiveData<List<Category>>()

    override fun refreshData() {
        if (futureCategories!=null && futureSubCategories!=null) {
            categories.value = futureCategories
            subcategories.value = futureSubCategories
            status.value = Status.SUCCESS
        } else {
            status.value = Status.ERROR
        }
    }

    override fun getCategories(): LiveData<List<Category>> {
        return categories
    }

    override fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>> {
        return Transformations.switchMap(subcategories) {
            filterOnParent(
                it,
                parentCategoryId
            )
        }
    }

    private fun filterOnParent(categories: List<Category>?, parentCategoryId: Int): LiveData<List<Category>>{
        val newLiveData = MutableLiveData<List<Category>>()
        newLiveData.value = categories?.filter { it.parent?.id == parentCategoryId }
        return newLiveData
    }

    override fun getRequestStatus(): LiveData<Status> {
        return status
    }
}