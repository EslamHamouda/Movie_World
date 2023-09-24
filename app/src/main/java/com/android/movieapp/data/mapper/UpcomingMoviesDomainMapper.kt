package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResultRemoteModel
import com.android.movieapp.data.model.response.upcomingMovies.UpcomingMoviesResultRemoteModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel

object UpcomingMoviesDomainMapper {
    fun toDomain(from: UpcomingMoviesResultRemoteModel) = UpcomingMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath
    )
}