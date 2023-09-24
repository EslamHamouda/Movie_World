package com.android.movieapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideGsonBuilder() = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
        setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(Interceptor { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOTE4ZmY0NGYyOTU5YWM4OTY5ZDc1YTA1ZWZmZWFlNiIsInN1YiI6IjY1MGUyYWE0M2E0YTEyMDExY2YyNGViMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KBa-9aThBbME0nchsM0ScRQJ6XmDglW5il9FAQtZeVA"
                    )
                    .build()
            )
        }).addInterceptor(logging).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()
}