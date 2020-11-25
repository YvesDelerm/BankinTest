package fr.ydelerm.bankintest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import fr.ydelerm.bankintest.api.NetworkModule
import fr.ydelerm.bankintest.persistence.PersistenceModule
import fr.ydelerm.bankintest.repository.RepositoryImpl
import fr.ydelerm.bankintest.repository.RepositoryModule
import fr.ydelerm.bankintest.ui.CategoriesActivity
import fr.ydelerm.bankintest.viewmodel.CategoriesViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ NetworkModule::class, PersistenceModule::class, RepositoryModule::class ] )
interface ApplicationGraph {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationGraph

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(categoriesActivity: CategoriesActivity)
    fun inject(repository: RepositoryImpl)
    fun inject(categoriesViewModel: CategoriesViewModel)
}