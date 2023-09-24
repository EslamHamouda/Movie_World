package com.android.movieapp.data.model.response.nowPlayingMovies

import com.google.gson.annotations.SerializedName

data class NowPlayingMoviesResponse(
    val dates: NowPlayingMoviesDatesRemoteModel,
    val page: Int,
    val results: List<NowPlayingMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)