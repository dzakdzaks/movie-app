package com.dzakdzaks.movieapp.domain.usecase

import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieappcore.model.movie.review.MovieReview
import com.dzakdzaks.movieappcore.model.pagination.PaginationResult
import com.dzakdzaks.movieappcore.network.response.WrapperResponse
import javax.inject.Inject

class FetchReviewMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    suspend operator fun invoke(page: Int = 1, movieId: Int): WrapperResponse<PaginationResult<MovieReview>> =
        repository.fetchReviewMovie(page = page, movieId = movieId)

}