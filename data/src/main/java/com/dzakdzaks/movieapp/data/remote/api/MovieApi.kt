package com.dzakdzaks.movieapp.data.remote.api

import com.dzakdzaks.movieapp.data.remote.model.movie.MovieData
import com.dzakdzaks.movieapp.data.remote.model.movie.detail.MovieDetailData
import com.dzakdzaks.movieapp.data.remote.model.movie.image.MovieImageData
import com.dzakdzaks.movieapp.data.remote.model.movie.review.MovieReviewData
import com.dzakdzaks.movieapp.data.remote.model.movie.video.MovieVideoData
import com.dzakdzaks.movieapp.data.remote.model.pagination.PaginationResultData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(NOW_PLAYING_MOVIE)
    suspend fun fetchNowPlayingMovies(
        @Query("page")
        page: Int = 1,
    ): PaginationResultData<MovieData>

    @GET(DETAIL_MOVIE)
    suspend fun fetchDetailMovie(
        @Path(MOVIE_ID) movieId: Int
    ): MovieDetailData

    @GET(REVIEW_MOVIE)
    suspend fun fetchReviewsMovie(
        @Path(MOVIE_ID) movieId: Int,
        @Query("page")
        page: Int = 1
    ): PaginationResultData<MovieReviewData>

    @GET(VIDEO_MOVIE)
    suspend fun fetchVideosMovie(
        @Path(MOVIE_ID) movieId: Int
    ): MovieVideoData

    @GET(IMAGE_MOVIE)
    suspend fun fetchImagesMovie(
        @Path(MOVIE_ID) movieId: Int
    ): MovieImageData

    companion object {
        const val MOVIE = "movie"
        const val MOVIE_ID = "movie_id"

        const val NOW_PLAYING_MOVIE = "$MOVIE/now_playing"
        const val DETAIL_MOVIE = "$MOVIE/{$MOVIE_ID}"
        const val REVIEW_MOVIE = "$MOVIE/{$MOVIE_ID}/reviews"
        const val VIDEO_MOVIE = "$MOVIE/{$MOVIE_ID}/videos"
        const val IMAGE_MOVIE = "$MOVIE/{$MOVIE_ID}/images"
    }

}