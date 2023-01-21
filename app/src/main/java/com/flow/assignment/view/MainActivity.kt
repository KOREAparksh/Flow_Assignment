package com.flow.assignment.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flow.assignment.R
import com.flow.assignment.adapter.MovieAdapter
import com.flow.assignment.databinding.ActivityMainBinding
import com.flow.assignment.model.Movie
import com.flow.assignment.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var isFirst:Boolean = true
    private lateinit var adapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = MovieAdapter(movieViewModel.movies.value ?: arrayListOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        binding.historyButton.setOnClickListener(onClickHistoryButton())
        binding.search.setOnQueryTextListener(setOnSearch())
        binding.recyclerView.addOnScrollListener(onScrollListener())
        setLoadingObserver()
        setMovieObserver()
    }

    private fun onClickHistoryButton() = View.OnClickListener {
        val intent = Intent(binding.root.context, HistoryActivity::class.java)
        startActivity(intent)
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

    private fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount - 1

            // 스크롤이 끝에 도달했는지 확인
            if (!binding.recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                isFirst = false
                movieViewModel.searchMore()
            }
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
    }
}