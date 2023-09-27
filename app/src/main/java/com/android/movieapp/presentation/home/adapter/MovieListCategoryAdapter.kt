package com.android.movieapp.presentation.home.adapter

import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.databinding.ListItemMovieListBinding
import com.android.movieapp.domain.model.MovieListCategoryDomainModel

class MovieListCategoryAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieListCategoryDomainModel, MovieListCategoryAdapter.ViewHolder>(
        ItemCallback
    ) {
    private var currentSelectedItemIndex: Int = 0
    private var previousSelectedItemIndex: Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.P)
        fun bind(item: MovieListCategoryDomainModel) {
            binding.tvMovieListName.text = item.name
            binding.tvMovieListName.typeface = (
                    if (bindingAdapterPosition == currentSelectedItemIndex) {
                        Typeface.create(null,500,false)
                    } else
                        Typeface.create(null,400,false)
                    )
            binding.line.visibility=(
                if (bindingAdapterPosition == currentSelectedItemIndex) {
                    View.VISIBLE
                } else
                    View.GONE
            )
            binding.root.setOnClickListener {
                previousSelectedItemIndex = currentSelectedItemIndex
                currentSelectedItemIndex = bindingAdapterPosition
                notifyItemChanged(previousSelectedItemIndex)
                notifyItemChanged(currentSelectedItemIndex)
                listener.onCategoryClicked(item.id)
            }
        }
    }

    private object ItemCallback : DiffUtil.ItemCallback<MovieListCategoryDomainModel>() {
        override fun areItemsTheSame(
            oldItem: MovieListCategoryDomainModel,
            newItem: MovieListCategoryDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListCategoryDomainModel,
            newItem: MovieListCategoryDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onCategoryClicked(categoryId: Int)
    }
}