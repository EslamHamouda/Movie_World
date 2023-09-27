package com.android.movieapp.data.service

import com.android.movieapp.data.model.response.nowPlayingMovies.NowPlayingMoviesResponse
import com.android.movieapp.data.model.response.popularMovies.PopularMoviesResponse
import com.android.movieapp.data.model.response.searchMovies.SearchMoviesResponse
import com.android.movieapp.data.model.response.topRatedMovies.TopRatedMoviesResponse
import com.android.movieapp.data.model.response.trendingMovies.TrendingMoviesResponse
import com.android.movieapp.data.model.response.upcomingMovies.UpcomingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): TrendingMoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") from: Int): NowPlayingMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") from: Int): UpcomingMoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") from: Int): TopRatedMoviesResponse

    @GET("movie/popular")
    suspend fun getPopularRatedMovies(@Query("page") from: Int): PopularMoviesResponse

    @GET("search/movie")
    suspend fun getSearchMovies(@Query("query") searchKey: String, @Query("page") from: Int): SearchMoviesResponse
}