package com.flow.assignment.service.dao

import androidx.room.*
import com.flow.assignment.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    suspend fun getAll(): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveHistory(history: History)
    
    @Delete
    suspend fun deleteHistory(history: History)
}