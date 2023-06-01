package com.dzakdzaks.movieapp.domain.repository

import com.dzakdzaks.movieapp.common.model.movie.Movie
import com.dzakdzaks.movieapp.common.model.movie.detail.MovieDetail
import com.dzakdzaks.movieapp.common.model.movie.image.MovieImage
import com.dzakdzaks.movieapp.common.model.movie.review.MovieReview
import com.dzakdzaks.movieapp.common.model.movie.video.MovieVideo
import com.dzakdzaks.movieapp.common.model.pagination.PaginationResult
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse

interface MovieRepository {
    suspend fun fetchNowPlayingMovies(page: Int = 1): WrapperResponse<PaginationResult<Movie>>
    suspend fun fetchDetailMovie(movieId: Int): WrapperResponse<MovieDetail>
    suspend fun fetchReviewMovie(page: Int = 1, movieId: Int): WrapperResponse<PaginationResult<MovieReview>>
    suspend fun fetchTrailerMovie(movieId: Int): WrapperResponse<MovieVideo>
    suspend fun fetchImageMovie(movieId: Int): WrapperResponse<MovieImage>
}