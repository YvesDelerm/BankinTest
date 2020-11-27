package fr.ydelerm.bankintest.api

import fr.ydelerm.bankintest.model.CategoryResult
import fr.ydelerm.bankintest.repository.NetworkDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val bankinApi: BankinApi) :
    NetworkDataSource {
    override fun getCategoriesTree(): Single<CategoryResult> {
        return bankinApi.getCategoriesTree()
    }
}