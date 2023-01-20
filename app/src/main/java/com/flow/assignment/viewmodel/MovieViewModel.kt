package com.flow.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flow.assignment.model.History
import com.flow.assignment.model.Movie
import com.flow.assignment.repository.HistoryRepository
import com.flow.assignment.repository.MovieRepository
import com.flow.assignment.util.Converters
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import kotlin.concurrent.thread

class MovieViewModel constructor(
    private val context: Context
) : ViewModel() {
    lateinit var movieRepository: MovieRepository
    lateinit var historyRepository: HistoryRepository
    private var searchQuery: String = ""
    private var totalResult: Int = 0
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _movies = MutableLiveData<ArrayList<Movie>>()
    val movies: LiveData<ArrayList<Movie>> get() = _movies

    init {
        movieRepository = MovieRepository()
        historyRepository = HistoryRepository(context)
        _isLoading.value = false
        _movies.value = ArrayList()
    }

    fun search(query: String){
        thread {
            _isLoading.postValue(true)
            searchQuery = query

            val movieDto = movieRepository.getMovies(query)
            _movies.postValue(movieDto.items)
            totalResult = movieDto.total

            //Todo history 추가
//            val history = History(query, Converters.dateToLong(LocalDateTime.now()))
//            historyRepository.saveHistory(history)

            _isLoading.postValue(false)
        }
    }

    fun searchMore(){
        if (_movies.value.isNullOrEmpty() || totalResult <= _movies.value!!.size){
            return
        }

        thread {
            _isLoading.postValue(true)

            val movieDto = movieRepository.getMovies(searchQuery, _movies.value!!.size + 1)
            _movies.postValue(movieDto.items)
            totalResult = movieDto.total
            //Todo history 추가
            _isLoading.postValue(false)
        }
    }
}