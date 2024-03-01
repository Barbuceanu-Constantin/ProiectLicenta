package com.example.proiectlicenta

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubcategorieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubcategorii(vararg categ: Subcategorii)

    @Insert
    suspend fun insertSubcategorie(subCateg: Subcategorii)

    @Update
    suspend fun updateSubcategorii(vararg subCateg: Subcategorii)

    @Delete
    suspend fun deleteSubcategorii(vararg subCateg: Subcategorii)

    @Query("SELECT * FROM Subcategorii")
    suspend fun loadAllSubcategorii(): Array<Subcategorii>
}