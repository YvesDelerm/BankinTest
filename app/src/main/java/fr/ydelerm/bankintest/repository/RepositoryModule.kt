package fr.ydelerm.bankintest.repository

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repository(application: Application): Repository {
        return RepositoryImpl(application)
    }
}