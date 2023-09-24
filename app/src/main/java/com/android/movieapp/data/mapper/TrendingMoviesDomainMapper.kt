package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResultRemoteModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

object TrendingMoviesDomainMapper {
    fun toDomain(from: TrendingMoviesResultRemoteModel) = TrendingMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath
    )
}