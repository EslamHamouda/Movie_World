package com.android.movieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.movieapp.databinding.ListItemTrendingMovieBinding
import com.android.movieapp.domain.model.TrendingMovieDomainModel

class TrendingMoviesAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TrendingMovieDomainModel, TrendingMoviesAdapter.ViewHolder>(ItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemTrendingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemTrendingMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingMovieDomainModel) {
            binding.ivTrendingMovie.load("https://image.tmdb.org/t/p/w500${item.posterImage}")
            binding.root.setOnClickListener {
                listener.onTrendingMovieClick(item.movieId)
            }
        }
    }
    private object ItemCallback : DiffUtil.ItemCallback<TrendingMovieDomainModel>() {
        override fun areItemsTheSame(oldItem: TrendingMovieDomainModel, newItem: TrendingMovieDomainModel): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: TrendingMovieDomainModel, newItem: TrendingMovieDomainModel): Boolean {
            return oldItem == newItem
        }
    }
    interface OnItemClickListener {
        fun onTrendingMovieClick(movieId:Int)
    }
}