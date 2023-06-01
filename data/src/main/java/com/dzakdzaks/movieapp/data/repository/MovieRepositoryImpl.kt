package com.dzakdzaks.movieapp.data.repository

import com.dzakdzaks.movieapp.data.mapper.toMovieDetail
import com.dzakdzaks.movieapp.data.mapper.toMovieImage
import com.dzakdzaks.movieapp.data.mapper.toMovieVideo
import com.dzakdzaks.movieapp.data.mapper.toPaginationResultMovie
import com.dzakdzaks.movieapp.data.mapper.toPaginationResultMovieReview
import com.dzakdzaks.movieapp.data.remote.api.MovieApi
import com.dzakdzaks.movieapp.data.remote.model.movie.video.MovieVideoData
import com.dzakdzaks.movieapp.domain.repository.MovieRepository
import com.dzakdzaks.movieapp.common.dispatcher.IoDispatcher
import com.dzakdzaks.movieapp.common.model.movie.Movie
import com.dzakdzaks.movieapp.common.model.movie.detail.MovieDetail
import com.dzakdzaks.movieapp.common.model.movie.image.MovieImage
import com.dzakdzaks.movieapp.common.model.movie.review.MovieReview
import com.dzakdzaks.movieapp.common.model.movie.video.MovieVideo
import com.dzakdzaks.movieapp.common.model.pagination.PaginationResult
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse
import com.dzakdzaks.movieapp.common.network.response.toError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    override suspend fun fetchNowPlayingMovies(page: Int): WrapperResponse<PaginationResult<Movie>> {
        return withContext(ioDispatcher) {
            try {
                val data = api.fetchNowPlayingMovies(page = page)
                if (data.results == null) {
                    WrapperResponse.Empty
                } else {
                    WrapperResponse.Success(data = data.toPaginationResultMovie())
                }
            } catch (e: Exception) {
                e.toError()
            }
        }
    }

    override suspend fun fetchDetailMovie(movieId: Int): WrapperResponse<MovieDetail> {
        return withContext(ioDispatcher) {
            try {
                val data = api.fetchDetailMovie(movieId = movieId)
                WrapperResponse.Success(data = data.toMovieDetail())
            } catch (e: Exception) {
                e.toError()
            }
        }
    }

    override suspend fun fetchReviewMovie(
        page: Int,
        movieId: Int,
    ): WrapperResponse<PaginationResult<MovieReview>> {
        return withContext(ioDispatcher) {
            try {
                val data = api.fetchReviewsMovie(page = page, movieId = movieId)
                if (data.results == null) {
                    WrapperResponse.Empty
                } else {
                    WrapperResponse.Success(data = data.toPaginationResultMovieReview())
                }
            } catch (e: Exception) {
                e.toError()
            }
        }
    }

    override suspend fun fetchTrailerMovie(movieId: Int): WrapperResponse<MovieVideo> {
        return withContext(ioDispatcher) {
            try {
                val data = api.fetchVideosMovie(movieId = movieId)
                val trailers = data.results?.filter { it.type == "Trailer" }
                val result = MovieVideoData(id = data.id, results = trailers).toMovieVideo()
                if (result.results.isEmpty()) {
                    WrapperResponse.Empty
                } else {
                    WrapperResponse.Success(data = result)
                }
            } catch (e: Exception) {
                e.toError()
            }
        }
    }

    override suspend fun fetchImageMovie(movieId: Int): WrapperResponse<MovieImage> {
        return withContext(ioDispatcher) {
            try {
                val data = api.fetchImagesMovie(movieId = movieId)
                WrapperResponse.Success(data = data.toMovieImage())
            } catch (e: Exception) {
                e.toError()
            }
        }
    }

}