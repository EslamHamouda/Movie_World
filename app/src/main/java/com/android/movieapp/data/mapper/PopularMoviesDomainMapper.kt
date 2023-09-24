package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.popularMovies.PopularMoviesResultRemoteModel
import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResultRemoteModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

object PopularMoviesDomainMapper {
    fun toDomain(from: PopularMoviesResultRemoteModel) = PopularMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath
    )
}