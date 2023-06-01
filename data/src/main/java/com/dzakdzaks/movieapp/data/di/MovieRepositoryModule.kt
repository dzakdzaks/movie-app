package com.dzakdzaks.movieapp.data.di

import com.dzakdzaks.movieapp.data.repository.MovieRepositoryImpl
import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    abstract fun bindsMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository
}