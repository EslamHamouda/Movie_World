package com.android.movieapp.presentation.home.viewmodel

import com.android.movieapp.domain.model.TrendingMovieDomainModel

sealed class TrendingMoviesHomeStates{
    data class Success(val value: List<TrendingMovieDomainModel>): TrendingMoviesHomeStates()
    data class Failure(val throwable: Throwable): TrendingMoviesHomeStates()
    data class Loading(val isLoading: Boolean): TrendingMoviesHomeStates()
}
