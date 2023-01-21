package com.flow.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.assignment.adapter.MovieAdapter
import com.flow.assignment.databinding.ActivityMainBinding
import com.flow.assignment.model.Movie
import com.flow.assignment.viewmodel.HistoryViewModel
import com.flow.assignment.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var isFirst:Boolean = true
    private lateinit var adapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
        adapter = MovieAdapter(movieViewModel.movies.value ?: arrayListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        binding.search.setOnQueryTextListener(setOnSearch())
        setLoadingObserver()
        setMovieObserver()
    }

    private fun setOnSearch() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {return true}
        override fun onQueryTextSubmit(query: String?): Boolean {
            binding.search.clearFocus()
            try {
                isFirst = true
                movieViewModel.search(query ?: "")
            } catch (e: Exception) {
                binding.textField.text = e.message
                println(e)
            }
            return true
        }
    }

    private fun setMovieObserver() {
        val observer = Observer<ArrayList<Movie>> {
            if (it.isEmpty()){
                binding.textField.visibility = View.VISIBLE
                binding.textField.text = baseContext.getString(R.string.empty_list)
            }
            else {
                binding.textField.visibility = View.GONE
            }

            if (isFirst) {
                adapter.setNewItems(it)
                isFirst = false;
            }
            else{
                adapter.addItems(it)
            }
        }
        movieViewModel.movies.observe(this, observer)
    }

    private fun setLoadingObserver(){
        val observer = Observer<Boolean> {
        if (it == false)
            binding.loading.visibility = View.GONE
        else
            binding.loading.visibility = View.VISIBLE
        }
        movieViewModel.isLoading.observe(this, observer)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var view = this.currentFocus
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        //Todo:
//        binding = null
        super.onDestroy()
        movieViewModel.viewModelScope.cancel()
        historyViewModel.viewModelScope.cancel()
    }
}