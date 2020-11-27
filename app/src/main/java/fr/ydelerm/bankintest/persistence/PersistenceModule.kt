package fr.ydelerm.bankintest.persistence

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fr.ydelerm.bankintest.repository.LocalDataSource
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun appDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "user-database"
        )
            .build()
    }

    @Singleton
    @Provides
    fun localDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSourceImpl(appDatabase)
    }
}