package com.flow.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.assignment.model.History
import com.flow.assignment.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private var historyRepository: HistoryRepository
) : ViewModel()  {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _histories = MutableLiveData<List<History>>()
    val histories: LiveData<List<History>> get() = _histories

    fun getHistories(){
        viewModelScope.launch (Dispatchers.IO) {
            _isLoading.postValue(true)
            runBlocking{
                launch { _histories.postValue(historyRepository.getAll()) }.join()
                launch { delay(500) }
            }
            _isLoading.postValue(false)
        }
    }

}