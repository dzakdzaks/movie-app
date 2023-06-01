package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieappcore.model.movie.image.MovieImage
import com.dzakdzaks.movieappcore.network.response.WrapperResponse
import javax.inject.Inject

class FetchImageMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): WrapperResponse<MovieImage> =
        repository.fetchImageMovie(movieId)
}