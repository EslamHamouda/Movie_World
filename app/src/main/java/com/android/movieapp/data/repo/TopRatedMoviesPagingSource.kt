package com.android.movieapp.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.movieapp.data.mapper.NowPlayingMoviesDomainMapper
import com.android.movieapp.data.mapper.TopRatedMoviesDomainMapper
import com.android.movieapp.data.service.MoviesService
import com.android.movieapp.domain.model.NowPlayingMovieDomainModel
import com.android.movieapp.domain.model.TopRatedMovieDomainModel
import com.android.movieapp.domain.model.TrendingMovieDomainModel

class TopRatedMoviesPagingSource(
    private val service: MoviesService
) :
    PagingSource<Int, TopRatedMovieDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedMovieDomainModel> {
        return try {
            val position = params.key ?: 1
            val response = service.getTopRatedMovies(position).results.map { dto ->
                TopRatedMoviesDomainMapper.toDomain(dto)
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

    override fun getRefreshKey(state: PagingState<Int, TopRatedMovieDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}