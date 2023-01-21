package com.flow.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.assignment.model.History
import com.flow.assignment.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class HistoryViewModel constructor(
    private val context: Context
) : ViewModel()  {
    private var historyRepository: HistoryRepository = HistoryRepository(context)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _histories = MutableLiveData<List<History>>()
    val histories: LiveData<List<History>> get() = _histories

    init {
        _isLoading.value = false
        _histories.value = mutableListOf()
    }

    fun getHistories(){
        viewModelScope.launch (Dispatchers.IO) {
            _isLoading.postValue(true)
            _histories.postValue(historyRepository.getAll())
            _isLoading.postValue(false)
        }
    }

}