package com.barbuceanuconstantin.proiectlicenta.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DatabaseDAO {
    @Query("DELETE FROM sqlite_sequence")
    fun clearPrimaryKeyIndex()
}