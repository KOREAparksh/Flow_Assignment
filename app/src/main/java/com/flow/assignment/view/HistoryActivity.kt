package com.flow.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.assignment.R
import com.flow.assignment.adapter.HistoryAdapter
import com.flow.assignment.databinding.ActivityHistoryBinding
import com.flow.assignment.model.History
import com.flow.assignment.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter : HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = HistoryAdapter(historyViewModel.histories.value ?: listOf())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        historyViewModel.getHistories()
        setLoadingObserver()
        setHistoryObserver()
    }
    private fun setHistoryObserver() {
        val observer = Observer<List<History>> {
            if (it.isEmpty()){
                binding.textField.visibility = View.VISIBLE
                binding.textField.text = baseContext.getString(R.string.empty_list)
            }
            else {
                binding.textField.visibility = View.GONE
            }

            adapter.setNewItems(it)
        }
        historyViewModel.histories.observe(this, observer)
    }

    private fun setLoadingObserver(){
        val observer = Observer<Boolean> {
            if (it == false)
                binding.loading.visibility = View.GONE
            else
                binding.loading.visibility = View.VISIBLE
        }
        historyViewModel.isLoading.observe(this, observer)
    }
}