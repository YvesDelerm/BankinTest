package fr.ydelerm.bankintest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import fr.ydelerm.bankintest.api.NetworkModule
import fr.ydelerm.bankintest.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ NetworkModule::class ] )
interface ApplicationGraph {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationGraph

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(mainActivity: MainActivity)
}