package com.android.movieapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.movieapp.data.mapper.TrendingMoviesDomainMapper
import com.android.movieapp.data.service.MoviesService
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel
import com.android.movieapp.domain.repo.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(private val service: MoviesService) : MoviesRepository {
    override suspend fun trendingMovies() =
        service.getTrendingMovies().results.map { dto ->
            TrendingMoviesDomainMapper.toDomain(
                dto
            )
        }

    override suspend fun nowPlayingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 50
            ),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource(service)
            }, initialKey = 1
        ).flow


    override suspend fun popularMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 50
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(service)
            }, initialKey = 1
        ).flow

    override suspend fun topRatedMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 50
            ),
            pagingSourceFactory = {
                TopRatedMoviesPagingSource(service)
            }, initialKey = 1
        ).flow

    override suspend fun upcomingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 50
            ),
            pagingSourceFactory = {
                UpcomingMoviesPagingSource(service)
            }, initialKey = 1
        ).flow
}