package com.barbuceanuconstantin.proiectlicenta.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.database.entity.Categories

@Dao
interface CategoryDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategorii(vararg categ: Categories)

   @Insert
   suspend fun insertCategorie(categ: Categories)

   @Update
   suspend fun updateCategorii(vararg categ: Categories)

   @Delete
   suspend fun deleteCategorii(vararg categ: Categories)

   @Query("SELECT * FROM Categories")
   suspend fun loadAllCategorii(): Array<Categories>
}