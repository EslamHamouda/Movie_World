package com.android.movieapp.data.model.response.topRatedMovies

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<TopRatedMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)