package com.example.proiectlicenta

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategorieDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategorii(vararg categ: Categorii)

   @Insert
   suspend fun insertCategorie(categ: Categorii)

   @Update
   suspend fun updateCategorii(vararg categ: Categorii)

   @Delete
   suspend fun deleteCategorii(vararg categ: Categorii)

   @Query("SELECT * FROM Categorii")
   suspend fun loadAllCategorii(): Array<Categorii>
}