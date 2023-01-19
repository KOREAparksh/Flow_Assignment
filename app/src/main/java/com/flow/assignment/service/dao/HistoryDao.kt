package com.flow.assignment.service.dao

import androidx.room.*
import com.flow.assignment.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveHistory(history: History)
    
    @Delete
    fun deleteHistory(history: History)
}