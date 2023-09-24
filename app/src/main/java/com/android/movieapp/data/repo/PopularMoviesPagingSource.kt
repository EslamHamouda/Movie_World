package com.android.movieapp.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.movieapp.data.mapper.NowPlayingMoviesDomainMapper
import com.android.movieapp.data.mapper.PopularMoviesDomainMapper
import com.android.movieapp.data.service.MoviesService
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.PopularMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

class PopularMoviesPagingSource(
    private val service: MoviesService
) :
    PagingSource<Int, PopularMovieDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieDomainModel> {
        return try {
            val position = params.key ?: 1
            val response = service.getPopularRatedMovies(position).results.map { dto ->
                PopularMoviesDomainMapper.toDomain(dto)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovieDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}