package com.android.movieapp.di

import com.android.movieapp.domain.repo.MoviesRepository
import com.android.movieapp.domain.useCase.GetNowPlayingMoviesUseCase
import com.android.movieapp.domain.useCase.GetPopularMoviesUseCase
import com.android.movieapp.domain.useCase.GetSearchMoviesUseCase
import com.android.movieapp.domain.useCase.GetTopRatedMoviesUseCase
import com.android.movieapp.domain.useCase.GetTrendingMoviesUseCase
import com.android.movieapp.domain.useCase.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetTrendingMoviesUseCase(repository: MoviesRepository): GetTrendingMoviesUseCase {
        return GetTrendingMoviesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetNowPlayingMoviesUseCase(repository: MoviesRepository): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetUpcomingMoviesUseCase(repository: MoviesRepository): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetTopRatedMoviesUseCase(repository: MoviesRepository): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetSearchMoviesUseCase(repository: MoviesRepository): GetSearchMoviesUseCase {
        return GetSearchMoviesUseCase(repository)
    }
}