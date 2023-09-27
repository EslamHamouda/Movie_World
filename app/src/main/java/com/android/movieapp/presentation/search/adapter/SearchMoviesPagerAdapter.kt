package com.android.movieapp.presentation.search.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.movieapp.databinding.ListItemMovieListContentBinding
import com.android.movieapp.databinding.ListItemMovieSearchBinding
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.SearchMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel

class SearchMoviesPagerAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<SearchMovieDomainModel, SearchMoviesPagerAdapter.ViewHolder>(ItemCallback) {
    inner class ViewHolder(private val binding: ListItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500${item?.posterImage}")
            binding.tvMovieName.text = item?.movieTitle
            binding.tvMovieRate.text = item?.movieRate.toString()
            binding.tvMovieYear.text = item?.movieReleaseDate
            binding.root.setOnClickListener {
                item?.let { it1 -> listener.onMovieClick(it1.movieId) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    private object ItemCallback: DiffUtil.ItemCallback<SearchMovieDomainModel>() {
        override fun areItemsTheSame(oldItem: SearchMovieDomainModel, newItem: SearchMovieDomainModel): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: SearchMovieDomainModel, newItem: SearchMovieDomainModel): Boolean {
            return oldItem == newItem
        }
    }
    interface OnItemClickListener{
        fun onMovieClick(movieId: Int)
    }
}