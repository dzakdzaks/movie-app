package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieapp.common.model.movie.video.MovieVideo
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse
import javax.inject.Inject

class FetchTrailerMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): WrapperResponse<MovieVideo> = repository.fetchTrailerMovie(movieId)
}