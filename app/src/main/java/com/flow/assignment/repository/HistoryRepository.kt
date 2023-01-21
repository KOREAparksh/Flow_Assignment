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
        val list: List<History> = getAll()
        if (list.any { h: History -> h.query == history.query }){
            historyDatabase.historyDao().saveHistory(history)
            return
        }
        if (list.size >= 10){
            historyDatabase.historyDao().deleteHistory(list.first())
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