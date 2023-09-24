package com.android.movieapp.domain.useCase

import androidx.paging.PagingData
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.repo.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.trendingMovies()
}