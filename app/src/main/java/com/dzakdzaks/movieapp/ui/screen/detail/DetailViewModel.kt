package com.dzakdzaks.movieapp.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.dzakdzaks.movieapp.data.repository.MovieReviewPagingSource
import com.dzakdzaks.movieapp.domain.usecase.FetchDetailMovieUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchImageMovieUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchReviewMoviesUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchTrailerMovieUseCase
import com.dzakdzaks.movieapp.ui.screen.navArgs
import com.dzakdzaks.movieappcore.network.response.WrapperResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchDetailMovieUseCase: FetchDetailMovieUseCase,
    private val fetchTrailerMovieUseCase: FetchTrailerMovieUseCase,
    private val fetchReviewMoviesUseCase: FetchReviewMoviesUseCase,
    private val fetchImageMovieUseCase: FetchImageMovieUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    private var getMovieDetailJob: Job? = null
    private var getMovieTrailerJob: Job? = null
    private var getMovieImageJob: Job? = null

    private val movieId = savedStateHandle.navArgs<DetailScreenNavArgs>().movieId

    val reviewsPaging = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
            initialLoadSize = 20,
        ),
        pagingSourceFactory = {
            MovieReviewPagingSource(
                fetchReviewMoviesUseCase = fetchReviewMoviesUseCase,
                movieId = movieId,
            )
        }
    ).flow.cachedIn(viewModelScope)

    init {
        getMovieImage(movieId = movieId)
        getMovieDetail(movieId = movieId)
        getMovieTrailer(movieId = movieId)
    }

    fun getMovieDetail(movieId: Int) {
        _state.update { it.copy(movieDetailLoading = true) }
        getMovieDetailJob?.cancel()
        getMovieDetailJob = viewModelScope.launch {
            when (val result = fetchDetailMovieUseCase(movieId = movieId)) {
                is WrapperResponse.Error -> {
                    _state.update {
                        it.copy(
                            movieDetailError = "Movie Detail: ${result.message}",
                            movieDetailLoading = false
                        )
                    }
                }

                is WrapperResponse.Success -> _state.update {
                    it.copy(
                        movieDetailError = "",
                        movieDetail = result.data,
                        movieDetailLoading = false
                    )
                }

                else -> {}
            }
        }
    }

    fun getMovieTrailer(movieId: Int) {
        _state.update { it.copy(movieTrailerLoading = true) }
        getMovieTrailerJob?.cancel()
        getMovieTrailerJob = viewModelScope.launch {
            fetchTrailerMovieUseCase(movieId = movieId)
            when (val result = fetchTrailerMovieUseCase(movieId = movieId)) {
                is WrapperResponse.Error -> _state.update {
                    it.copy(
                        movieTrailerError = "Movie Trailers: ${result.message}",
                        movieTrailerLoading = false
                    )
                }

                is WrapperResponse.Success -> _state.update {
                    it.copy(
                        movieTrailerError = "",
                        movieTrailers = result.data.results,
                        movieTrailerLoading = false
                    )
                }

                is WrapperResponse.Empty -> _state.update {
                    it.copy(
                        movieTrailerError = "No trailer available.",
                        movieTrailerLoading = false
                    )
                }
            }
        }
    }

    fun getMovieImage(movieId: Int) {
        _state.update { it.copy(movieImageLoading = true) }
        getMovieImageJob?.cancel()
        getMovieImageJob = viewModelScope.launch {
            fetchImageMovieUseCase(movieId = movieId)
            when (val result = fetchImageMovieUseCase(movieId = movieId)) {
                is WrapperResponse.Error -> _state.update {
                    it.copy(
                        movieImageError = "Movie Images: ${result.message}",
                        movieImageLoading = false
                    )
                }

                is WrapperResponse.Success -> _state.update {
                    it.copy(
                        movieImageError = "",
                        movieImages = result.data.movieImageBackdrops,
                        movieImageLoading = false
                    )
                }

                is WrapperResponse.Empty -> _state.update {
                    it.copy(
                        movieImageError = "No image available.",
                        movieImageLoading = false
                    )
                }
            }
        }
    }
}