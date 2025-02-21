package com.apexascent.assignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TarotDao {
    @Insert
    suspend fun saveResult(tarotResult: TarotResult)
    @Query("DELETE FROM TarotResult")
    suspend fun clearAll()
    @Query("SELECT * FROM TarotResult")
    suspend fun getAllResults(): List<TarotResult>
}