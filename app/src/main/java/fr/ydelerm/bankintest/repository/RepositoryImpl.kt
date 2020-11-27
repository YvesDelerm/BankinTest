package fr.ydelerm.bankintest.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.vo.Status
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl(
    application: Application,
    private val scheduler: @NonNull Scheduler = Schedulers.io()
) : Repository {

    init {
        (application as BankinApplication)
            .appGraph.inject(this)
    }

    companion object {
        private const val LOGTAG = "RepositoryImpl"
    }

    @Inject
    lateinit var networkDataSource: NetworkDataSource

    @Inject
    lateinit var localDataSource: LocalDataSource

    private val requestStatus = MutableLiveData<Status>()

    override fun refreshData() {
        requestStatus.postValue(Status.LOADING)
        networkDataSource.getCategoriesTree()
            .subscribeOn(scheduler)
            .subscribe(
                { categoriesResult ->
                    run {
                        localDataSource.insertCategories(categoriesResult.categories)
                        requestStatus.postValue(Status.SUCCESS)
                    }
                },
                { exception ->
                    run {
                        Log.e(LOGTAG, "error while getting categories", exception)
                        requestStatus.postValue(Status.ERROR)
                    }
                }
            )

    }

    override fun getRequestStatus(): LiveData<Status> {
        return requestStatus
    }

    override fun getCategories(): LiveData<List<Category>> {
        return localDataSource.getCategories()
    }

    override fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>> {
        return localDataSource.getSubCategories(parentCategoryId)
    }
}