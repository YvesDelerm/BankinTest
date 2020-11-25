package fr.ydelerm.bankintest.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.repository.Repository
import fr.ydelerm.bankintest.ui.CategoriesActivity
import fr.ydelerm.bankintest.ui.CategoryClickListener
import fr.ydelerm.bankintest.vo.Status
import javax.inject.Inject

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    var selectedCategoryId: Int? = null

    @Inject
    lateinit var repository: Repository

    init {
        (application as BankinApplication).appGraph.inject(this)
    }

    fun refreshData() {
        repository.refreshData()
    }

    fun getRequestStatus(): LiveData<Status> {
        return repository.getRequestStatus()
    }

    fun getCategories(): LiveData<List<Category>> {
        //TODO unit test si categories ou sous categories et si param passé
        return selectedCategoryId?.let {
            repository.getSubCategories(it)
        } ?: repository.getCategories()
    }

    fun getCategoryClickListener(context: Context): CategoryClickListener? {
        //TODO unit test
        return if (selectedCategoryId==null) {
            object: CategoryClickListener {
                override fun onCategoryClicked(category: Category) {
                    val toSubCategories = Intent(context, CategoriesActivity::class.java)
                    toSubCategories.putExtra(
                        CategoriesActivity.PARAM_SELECTED_CATEGORY_ID,
                        category.id
                    )
                    context.startActivity(toSubCategories)
                }
            }
        } else {
            null
        }
    }

    fun isDisplayHomeAsUpEnabled(): Boolean {
        //TODO unit test
        return (selectedCategoryId!=null)
    }

}