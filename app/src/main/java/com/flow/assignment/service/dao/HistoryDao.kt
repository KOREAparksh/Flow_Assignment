package com.flow.assignment.service.dao

import androidx.room.*
import com.flow.assignment.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    suspend fun getAll(): List<History>

    @Query("SELECT * FROM history LIMIT 1")
    suspend fun getFirst(): History

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveHistory(history: History)
    
    @Delete
    suspend fun deleteHistory(history: History)

    @Query("DELETE  FROM history")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) as cnt FROM history")
    suspend fun countHistory(): Int
}