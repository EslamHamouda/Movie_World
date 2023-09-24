package com.android.movieapp.domain.useCase

import androidx.paging.PagingData
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel
import com.android.movieapp.domain.repo.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetUpcomingMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.upcomingMovies()
}