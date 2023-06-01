package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieappcore.model.movie.Movie
import com.dzakdzaks.movieappcore.model.pagination.PaginationResult
import com.dzakdzaks.movieappcore.network.response.WrapperResponse
import javax.inject.Inject

class FetchNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend operator fun invoke(page: Int = 1): WrapperResponse<PaginationResult<Movie>> =
        repository.fetchNowPlayingMovies(page = page)

}