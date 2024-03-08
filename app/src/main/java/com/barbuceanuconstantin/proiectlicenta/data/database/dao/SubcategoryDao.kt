package com.barbuceanuconstantin.proiectlicenta.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barbuceanuconstantin.proiectlicenta.data.database.entity.Subcategorys

@Dao
interface SubcategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubcategorys(vararg categ: Subcategorys)
    @Insert
    suspend fun insertSubcategory(subCateg: Subcategorys)
    @Update
    suspend fun updateSubcategorys(vararg subCateg: Subcategorys)
    @Delete
    suspend fun deleteSubcategorys(vararg subCateg: Subcategorys)
    @Query("SELECT * FROM Subcategorys")
    suspend fun loadAllSubcategorys(): Array<Subcategorys>
}