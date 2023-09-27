package com.android.movieapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.SearchMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel
import com.android.movieapp.domain.useCase.GetNowPlayingMoviesUseCase
import com.android.movieapp.domain.useCase.GetPopularMoviesUseCase
import com.android.movieapp.domain.useCase.GetSearchMoviesUseCase
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
class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,

) : ViewModel() {

    private var _getSearchMoviesResponse: Flow<PagingData<SearchMovieDomainModel>> = emptyFlow()
    val getSearchMoviesResponse
        get() = _getSearchMoviesResponse

    fun getSearchMovies(searchKey: String) {
        viewModelScope.launch {
            _getSearchMoviesResponse =
                getSearchMoviesUseCase(searchKey).cachedIn(viewModelScope).flowOn(Dispatchers.IO)
        }
    }
}