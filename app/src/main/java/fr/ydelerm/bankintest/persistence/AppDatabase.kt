package fr.ydelerm.bankintest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ydelerm.bankintest.model.Category

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun resourceDAO(): ResourceDAO
}