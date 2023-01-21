package com.flow.assignment.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flow.assignment.databinding.ItemMovieBinding
import com.flow.assignment.model.Movie

class MovieAdapter
    (
    private var items: ArrayList<Movie>
    )
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, position: Int) {
            binding.movie = movie
            Glide.with(binding.root.context)
                .load(movie.image)
                .into(binding.moviePoster)
            binding.item.setOnClickListener{
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(movie.link)
                binding.root.context.startActivity(i)
            }
        }
    }


    fun setNewItems(newList: ArrayList<Movie>) {
        notifyItemRangeRemoved(0, items.size)
        this.items = newList
        notifyItemRangeInserted(0, items.size)
    }

    fun addItems(newList: ArrayList<Movie>) {
        this.items = newList
        if (newList.size != 0)
            notifyItemInserted(newList.size)
    }
}