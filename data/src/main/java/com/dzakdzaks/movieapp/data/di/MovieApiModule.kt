package com.dzakdzaks.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.dzakdzaks.movieapp.data.local.MoviesDao
import com.dzakdzaks.movieapp.data.local.MoviesDatabase
import com.dzakdzaks.movieapp.data.local.RemoteKeysDao
import com.dzakdzaks.movieapp.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieApiModule {
    @Provides
    @Singleton
    fun providesDataApi(
        retrofit: Retrofit,
    ): MovieApi = retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room
            .databaseBuilder(context, MoviesDatabase::class.java, "movie_db")
            .build()

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao = moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MoviesDatabase): RemoteKeysDao = moviesDatabase.getRemoteKeysDao()

}