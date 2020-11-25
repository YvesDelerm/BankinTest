package fr.ydelerm.bankintest.persistence

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
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
    fun resourceDAO(appDatabase: AppDatabase): ResourceDAO {
        return appDatabase.resourceDAO()
    }
}