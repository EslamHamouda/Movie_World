package com.android.movieapp.domain.repo

import androidx.paging.PagingData
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.SearchMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun trendingMovies(): List<TrendingMovieDomainModel>
    suspend fun nowPlayingMovies(): Flow<PagingData<NowPlayingMovieDomainModel>>
    suspend fun popularMovies(): Flow<PagingData<PopularMovieDomainModel>>
    suspend fun topRatedMovies(): Flow<PagingData<TopRatedMovieDomainModel>>
    suspend fun upcomingMovies(): Flow<PagingData<UpcomingMovieDomainModel>>
    suspend fun searchMovies(searchKey: String): Flow<PagingData<SearchMovieDomainModel>>
}