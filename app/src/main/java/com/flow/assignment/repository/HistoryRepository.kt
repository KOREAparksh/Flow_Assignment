package com.flow.assignment.repository

import android.content.Context
import com.flow.assignment.database.HistoryDatabase
import com.flow.assignment.model.History
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val historyDatabase: HistoryDatabase
    ) {

    suspend fun getAll() : List<History>{
        return historyDatabase.historyDao().getAll()
    }

    suspend fun saveHistory(history: History){
        if (historyDatabase.historyDao().countHistory() >= 10){
            val temp: History = historyDatabase.historyDao().getFirst()
            historyDatabase.historyDao().deleteHistory(temp)
        }
        historyDatabase.historyDao().saveHistory(history)
    }

    suspend fun deleteHistory(history: History){
        historyDatabase.historyDao().deleteHistory(history)
    }

    suspend fun deleteAll(){
        historyDatabase.historyDao().deleteAll()
    }
}