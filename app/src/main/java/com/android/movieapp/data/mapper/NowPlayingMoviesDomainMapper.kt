package com.android.movieapp.data.mapper

import com.android.movieapp.data.model.response.nowPlayingMovies.NowPlayingMoviesResultRemoteModel
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel

object NowPlayingMoviesDomainMapper {
    fun toDomain(from: NowPlayingMoviesResultRemoteModel) = NowPlayingMovieDomainModel(
        movieId = from.id,
        posterImage = from.posterPath
    )
}