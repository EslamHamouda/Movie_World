package com.android.movieapp.data.model.response.upcomingMovies

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesResponse(
    val dates: UpcomingMoviesDatesRemoteModel,
    val page: Int,
    val results: List<UpcomingMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)