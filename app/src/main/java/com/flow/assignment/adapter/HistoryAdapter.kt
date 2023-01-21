package com.flow.assignment.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.flow.assignment.databinding.ItemHistoryBinding
import com.flow.assignment.model.History
import com.flow.assignment.util.Converters
import com.flow.assignment.view.MainActivity

class HistoryAdapter(
    private var items: List<History>,
    val onTapItem: (String)->Unit,
)
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.query = history.query
            binding.time = Converters.longToDate(history.createAt).toString()
            binding.item.setOnClickListener{
                onTapItem(history.query)
            }
        }
    }

    fun setNewItems(newItems: List<History>){
        items = newItems
        items =  items.reversed()
        notifyItemRangeInserted(0, items.size)
    }

}