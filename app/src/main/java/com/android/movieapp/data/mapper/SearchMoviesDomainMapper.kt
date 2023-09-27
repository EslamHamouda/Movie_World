package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.popularMovies.PopularMoviesResultRemoteModel
import com.android.movieapp.data.model.response.searchMovies.SearchMoviesResultRemoteModel
import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResultRemoteModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.SearchMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

object SearchMoviesDomainMapper {
    fun toDomain(from: SearchMoviesResultRemoteModel) = SearchMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath,
        genreId = from.genreIds[0],
        movieRate = from.voteAverage,
        movieReleaseDate = from.releaseDate,
        movieTitle = from.originalTitle,
        runtime = 0
    )
}