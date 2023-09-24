package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.topRatedMovies.TopRatedMoviesResultRemoteModel
import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResultRemoteModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

object TopRatedMoviesDomainMapper {
    fun toDomain(from: TopRatedMoviesResultRemoteModel) = TopRatedMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath
    )
}