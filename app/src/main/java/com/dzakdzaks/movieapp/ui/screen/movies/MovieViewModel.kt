package com.dzakdzaks.movieapp.ui.screen.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dzakdzaks.movieapp.data.local.MoviesDatabase
import com.dzakdzaks.movieapp.data.repository.MoviesRemoteMediator
import com.dzakdzaks.movieapp.domain.usecase.FetchNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    private val db: MoviesDatabase,
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val nowPlayingMoviesPaging = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
            initialLoadSize = 20,
        ),
        remoteMediator = MoviesRemoteMediator(
            fetchNowPlayingMoviesUseCase,
            db,
        ),
        pagingSourceFactory = {
            db.getMoviesDao().getMovies()
        },
    ).flow.cachedIn(viewModelScope)
}