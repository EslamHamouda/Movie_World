package com.android.movieapp.domain.useCase

import androidx.paging.PagingData
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.repo.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.topRatedMovies()
}