package com.flow.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.assignment.model.Movie
import com.flow.assignment.repository.HistoryRepository
import com.flow.assignment.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val historyRepository: HistoryRepository,
) : ViewModel() {
    private var searchQuery: String = ""
    private var totalResult: Int = 0
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _movies = MutableLiveData<ArrayList<Movie>>()
    val movies: LiveData<ArrayList<Movie>> get() = _movies
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    init {
        _isLoading.value = false
        _movies.value = ArrayList()
    }

    fun search(query: String){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler)  {
            _isLoading.postValue(true)
            searchQuery = query

            try {
                val movieDto = movieRepository.getMovies(query)
                _movies.postValue(movieDto.items)
                totalResult = movieDto.total
            }catch (e : Exception){
                //Todo: error Dialog
            }finally {
                _isLoading.postValue(false)
            }

            //Todo history 추가
        }
    }

    fun searchMore(){
        if (_movies.value.isNullOrEmpty() || totalResult <= _movies.value!!.size){
            return
        }

        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler) {
            _isLoading.postValue(true)

            try {
                val movieDto = movieRepository.getMovies(searchQuery, _movies.value!!.size + 1)
                _movies.postValue(movieDto.items)
                totalResult = movieDto.total
            }catch (e : Exception){
                //Todo: error Dialog
            }finally {
                _isLoading.postValue(false)
            }

            //Todo history 추가
        }
    }
}