package fr.ydelerm.bankintest.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ydelerm.bankintest.model.Category

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<Category>)

    @Query("SELECT * FROM category where parentId is null")
    fun getCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category where parentId=:parentCategoryId")
    fun getSubCategories(parentCategoryId: Int): LiveData<List<Category>>
}