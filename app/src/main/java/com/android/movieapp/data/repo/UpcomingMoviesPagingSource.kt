package com.android.movieapp.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.movieapp.data.mapper.NowPlayingMoviesDomainMapper
import com.android.movieapp.data.mapper.UpcomingMoviesDomainMapper
import com.android.movieapp.data.service.MoviesService
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel
import com.android.movieapp.domain.model.UpcomingMovieDomainModel

class UpcomingMoviesPagingSource(
    private val service: MoviesService
) :
    PagingSource<Int, UpcomingMovieDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMovieDomainModel> {
        return try {
            val position = params.key ?: 1
            val response = service.getUpcomingMovies(position).results.map { dto ->
                UpcomingMoviesDomainMapper.toDomain(dto)
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

    override fun getRefreshKey(state: PagingState<Int, UpcomingMovieDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}