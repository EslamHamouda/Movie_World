package com.android.movieapp.presentation.home.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.movieapp.databinding.ListItemMovieListContentBinding
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel

class UpcomingMoviesPagerAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<UpcomingMovieDomainModel, UpcomingMoviesPagerAdapter.ViewHolder>(ItemCallback) {
    inner class ViewHolder(private val binding: ListItemMovieListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.ivMovie.load("https://image.tmdb.org/t/p/w500${item?.posterImage}")
            binding.root.setOnClickListener {
                item?.let { it1 -> listener.onMovieClick(it1.movieId) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemMovieListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    private object ItemCallback: DiffUtil.ItemCallback<UpcomingMovieDomainModel>() {
        override fun areItemsTheSame(oldItem: UpcomingMovieDomainModel, newItem: UpcomingMovieDomainModel): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: UpcomingMovieDomainModel, newItem: UpcomingMovieDomainModel): Boolean {
            return oldItem == newItem
        }
    }
    interface OnItemClickListener{
        fun onMovieClick(movieId: Int)
    }
}