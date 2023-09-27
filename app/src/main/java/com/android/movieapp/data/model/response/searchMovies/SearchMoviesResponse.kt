package com.android.movieapp.data.model.response.searchMovies

import com.google.gson.annotations.SerializedName

data class SearchMoviesResponse(
    val page: Int,
    val results: List<SearchMoviesResultRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)