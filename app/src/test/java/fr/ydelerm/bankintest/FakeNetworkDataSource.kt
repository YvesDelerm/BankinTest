package fr.ydelerm.bankintest

import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.model.CategoryResult
import fr.ydelerm.bankintest.repository.NetworkDataSource
import io.reactivex.rxjava3.core.Single

class FakeNetworkDataSource(private val categories: List<Category>?) : NetworkDataSource {
    override fun getCategoriesTree(): Single<CategoryResult> {
        return if (categories == null) {
            Single.error(Throwable("fake error"))
        } else {
            Single.just(CategoryResult(categories))
        }
    }

}
