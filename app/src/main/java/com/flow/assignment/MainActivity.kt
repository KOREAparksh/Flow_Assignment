package com.flow.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.flow.assignment.viewmodel.HistoryViewModel
import com.flow.assignment.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieViewModel.search("1")
        historyViewModel.getHistories()
        lifecycleScope.launch {
            delay(1000)
            movieViewModel.searchMore()
        }
    }
}