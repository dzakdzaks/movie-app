package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieapp.common.model.movie.Movie
import com.dzakdzaks.movieapp.common.model.pagination.PaginationResult
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse
import javax.inject.Inject

class FetchNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend operator fun invoke(page: Int = 1): WrapperResponse<PaginationResult<Movie>> =
        repository.fetchNowPlayingMovies(page = page)

}