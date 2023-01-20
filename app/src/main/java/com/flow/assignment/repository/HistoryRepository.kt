package com.flow.assignment.repository

import android.content.Context
import com.flow.assignment.database.HistoryDatabase
import com.flow.assignment.model.History
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HistoryRepository
        @Inject constructor(
            @ApplicationContext private val context: Context
        ) {
    private val historyDatabase: HistoryDatabase = HistoryDatabase.getInstance(context)!!

    fun getAll() : List<History>{
        return historyDatabase.historyDao().getAll()
    }

    fun saveHistory(history: History){
        // Todo: 10개 이상 시 삭제
        historyDatabase.historyDao().saveHistory(history)
    }

    fun deleteHistory(history: History){
        historyDatabase.historyDao().deleteHistory(history)
    }
}