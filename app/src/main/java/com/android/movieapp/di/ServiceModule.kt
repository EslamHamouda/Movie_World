package com.android.movieapp.di

import com.android.movieapp.data.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun detailsService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)
}