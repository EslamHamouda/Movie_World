package com.android.movieapp.domain.model

data class SearchMovieDomainModel(
    val movieId: Int,
    val posterImage: String,
    val movieTitle: String,
    val movieRate: Double,
    val genreId: Int,
    val movieReleaseDate: String,
    val runtime: Int
)
