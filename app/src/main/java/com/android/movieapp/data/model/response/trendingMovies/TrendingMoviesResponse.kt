package com.android.movieapp.data.model.response.trendingMovies

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    val page: Int,
    val results: List<TrendingMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)