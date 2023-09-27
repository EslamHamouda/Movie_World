package com.android.movieapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel
import com.android.movieapp.domain.useCase.GetNowPlayingMoviesUseCase
import com.android.movieapp.domain.useCase.GetPopularMoviesUseCase
import com.android.movieapp.domain.useCase.GetTopRatedMoviesUseCase
import com.android.movieapp.domain.useCase.GetTrendingMoviesUseCase
import com.android.movieapp.domain.useCase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase

) : ViewModel() {

    private val _getTrendingMoviesResponse: MutableStateFlow<TrendingMoviesHomeStates> =
        MutableStateFlow(TrendingMoviesHomeStates.Loading(true))
    val getVendorCategoryResponse = _getTrendingMoviesResponse.asStateFlow()

    fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { getTrendingMoviesUseCase() }
                _getTrendingMoviesResponse.value = TrendingMoviesHomeStates.Loading(false)
                _getTrendingMoviesResponse.value = TrendingMoviesHomeStates.Success(result)
            } catch (throwable: Throwable) {
                _getTrendingMoviesResponse.value = TrendingMoviesHomeStates.Loading(false)
                _getTrendingMoviesResponse.value = TrendingMoviesHomeStates.Failure(throwable)
            }
        }
    }

    private var _getNowPlayingMoviesResponse: Flow<PagingData<NowPlayingMovieDomainModel>> = emptyFlow()
    val getNowPlayingMoviesResponse
        get() = _getNowPlayingMoviesResponse

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _getNowPlayingMoviesResponse =
                getNowPlayingMoviesUseCase().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
        }
    }

    private var _getUpcomingMoviesResponse: Flow<PagingData<UpcomingMovieDomainModel>> = emptyFlow()
    val getUpcomingMoviesResponse
        get() = _getUpcomingMoviesResponse

    fun getUpcomingMovies() {
        viewModelScope.launch {
            _getUpcomingMoviesResponse =
                getUpcomingMoviesUseCase().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
        }
    }

    private var _getTopRatedMoviesResponse: Flow<PagingData<TopRatedMovieDomainModel>> = emptyFlow()
    val getTopRatedMoviesResponse
        get() = _getTopRatedMoviesResponse

    fun getTopRatedMovies() {
        viewModelScope.launch {
            _getTopRatedMoviesResponse =
                getTopRatedMoviesUseCase().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
        }
    }

    private var _getPopularMoviesResponse: Flow<PagingData<PopularMovieDomainModel>> = emptyFlow()
    val getPopularMoviesResponse
        get() = _getPopularMoviesResponse

    fun getPopularMovies() {
        viewModelScope.launch {
            _getPopularMoviesResponse =
                getPopularMoviesUseCase().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
        }
    }
}