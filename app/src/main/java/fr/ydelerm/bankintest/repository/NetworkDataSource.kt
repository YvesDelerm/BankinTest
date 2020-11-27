package fr.ydelerm.bankintest.repository

import fr.ydelerm.bankintest.model.CategoryResult
import io.reactivex.rxjava3.core.Single

interface NetworkDataSource {
    fun getCategoriesTree(): Single<CategoryResult>
}