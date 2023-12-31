package com.android.movieapp.data.model.response.popularMovies

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Int,
    val results: List<PopularMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)