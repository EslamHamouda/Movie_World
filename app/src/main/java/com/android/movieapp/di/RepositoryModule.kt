package com.android.movieapp.di

import com.android.movieapp.data.repo.MoviesRepositoryImpl
import com.android.movieapp.data.service.MoviesService
import com.android.movieapp.domain.repo.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(service: MoviesService): MoviesRepository {
        return MoviesRepositoryImpl(service)
    }
}