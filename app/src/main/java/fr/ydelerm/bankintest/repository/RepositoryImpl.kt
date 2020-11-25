package fr.ydelerm.bankintest.repository

import android.app.Application
import androidx.lifecycle.LiveData
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.api.BankinApi
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.persistence.ResourceDAO
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl(application: Application) : Repository {

    init {
        (application as BankinApplication)
            .appGraph.inject(this)
    }
    //TODO abstraire en passant par des DataSource
    @Inject
    lateinit var bankinApi: BankinApi
    @Inject
    lateinit var resourceDAO: ResourceDAO

    override fun refreshData() {
        bankinApi.getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe { t1, t2 -> resourceDAO.insertResources(t1.categories) } //TODO gerer erreurs

    }

    override fun getCategories(): LiveData<List<Category>> {
        return resourceDAO.getCategories()
    }

    override fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>> {
        return resourceDAO.getSubCategories(parentCategoryId)
    }
}