package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieapp.common.model.movie.image.MovieImage
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse
import javax.inject.Inject

class FetchImageMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): WrapperResponse<MovieImage> =
        repository.fetchImageMovie(movieId)
}