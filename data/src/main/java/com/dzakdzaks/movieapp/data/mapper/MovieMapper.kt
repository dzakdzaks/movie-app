package com.dzakdzaks.movieapp.data.mapper

import com.dzakdzaks.movieapp.data.remote.model.movie.MovieData
import com.dzakdzaks.movieapp.data.remote.model.movie.detail.GenreData
import com.dzakdzaks.movieapp.data.remote.model.movie.detail.MovieDetailData
import com.dzakdzaks.movieapp.data.remote.model.movie.image.MovieImageBackdropData
import com.dzakdzaks.movieapp.data.remote.model.movie.image.MovieImageData
import com.dzakdzaks.movieapp.data.remote.model.movie.review.AuthorDetailsData
import com.dzakdzaks.movieapp.data.remote.model.movie.review.MovieReviewData
import com.dzakdzaks.movieapp.data.remote.model.movie.video.MovieVideoData
import com.dzakdzaks.movieapp.data.remote.model.movie.video.ResultMovieVideoData
import com.dzakdzaks.movieapp.data.remote.model.pagination.DatesData
import com.dzakdzaks.movieapp.data.remote.model.pagination.PaginationResultData
import com.dzakdzaks.movieappcore.ext.orEmpty
import com.dzakdzaks.movieappcore.model.movie.Movie
import com.dzakdzaks.movieappcore.model.movie.detail.Genre
import com.dzakdzaks.movieappcore.model.movie.detail.MovieDetail
import com.dzakdzaks.movieappcore.model.movie.image.MovieImage
import com.dzakdzaks.movieappcore.model.movie.image.MovieImageBackdrop
import com.dzakdzaks.movieappcore.model.movie.review.AuthorDetails
import com.dzakdzaks.movieappcore.model.movie.review.MovieReview
import com.dzakdzaks.movieappcore.model.movie.video.MovieVideo
import com.dzakdzaks.movieappcore.model.movie.video.ResultMovieVideo
import com.dzakdzaks.movieappcore.model.pagination.Dates
import com.dzakdzaks.movieappcore.model.pagination.PaginationResult

fun PaginationResultData<MovieData>?.toPaginationResultMovie(): PaginationResult<Movie> =
    PaginationResult(
        datesData = this?.datesData.toDates(),
        id = this?.id.orEmpty(-1),
        page = this?.page.orEmpty(),
        results = this?.results?.map { it.toMovie() }.orEmpty(),
        totalPages = this?.totalPages.orEmpty(),
        totalResults = this?.totalResults.orEmpty()
    )

private fun DatesData?.toDates(): Dates = Dates(
    minimum = this?.minimum.orEmpty(),
    maximum = this?.maximum.orEmpty()
)

private fun MovieData?.toMovie(): Movie = Movie(
    id = this?.id.orEmpty(-1),
    originalTitle = this?.originalTitle.orEmpty(),
    posterPath = this?.posterPath.orEmpty(),
    releaseDate = this?.releaseDate.orEmpty(),
    title = this?.title.orEmpty(),
    voteAverage = this?.voteAverage.orEmpty(),
    page = 0,
)

fun MovieDetailData?.toMovieDetail() = MovieDetail(
    adult = this?.adult.orEmpty(),
    backdropPath = this?.backdropPath.orEmpty(),
    genres = this?.genres?.map { it.toGenre() }.orEmpty(),
    homepage = this?.homepage.orEmpty(),
    id = this?.id.orEmpty(-1),
    originalTitle = this?.originalTitle.orEmpty(),
    overview = this?.overview.orEmpty(),
    posterPath = this?.posterPath.orEmpty(),
    releaseDate = this?.releaseDate.orEmpty(),
    tagline = this?.tagline.orEmpty(),
    title = this?.title.orEmpty(),
    voteAverage = this?.voteAverage.orEmpty(),
    voteCount = this?.voteCount.orEmpty(),
    runtime = this?.runtime.orEmpty()
)

private fun GenreData?.toGenre() = Genre(
    id = this?.id.orEmpty(),
    name = this?.name.orEmpty()
)

fun PaginationResultData<MovieReviewData>?.toPaginationResultMovieReview(): PaginationResult<MovieReview> =
    PaginationResult(
        datesData = this?.datesData.toDates(),
        id = this?.id.orEmpty(-1),
        page = this?.page.orEmpty(),
        results = this?.results?.map { it.toMovieReview() }.orEmpty(),
        totalPages = this?.totalPages.orEmpty(),
        totalResults = this?.totalResults.orEmpty()
    )

private fun MovieReviewData?.toMovieReview(): MovieReview = MovieReview(
    author = this?.author.orEmpty(),
    authorDetails = this?.authorDetails.toAuthorDetail(),
    content = this?.content.orEmpty(),
    createdAt = this?.createdAt.orEmpty(),
    id = this?.id.orEmpty(),
    updatedAt = this?.updatedAt.orEmpty(),
    url = this?.url.orEmpty()
)

private fun AuthorDetailsData?.toAuthorDetail() = AuthorDetails(
    avatarPath = this?.avatarPath.orEmpty(),
    name = this?.name.orEmpty(),
    rating = this?.rating.orEmpty(),
    username = this?.username.orEmpty()
)

fun MovieVideoData?.toMovieVideo() = MovieVideo(
    id = this?.id.orEmpty(-1),
    results = this?.results?.map { it.toResultMovieVideo() }.orEmpty()
)

private fun ResultMovieVideoData?.toResultMovieVideo() = ResultMovieVideo(
    id = this?.id.orEmpty(),
    key = this?.key.orEmpty(),
    name = this?.name.orEmpty(),
    type = this?.type.orEmpty()
)

fun MovieImageData.toMovieImage() = MovieImage(
    movieImageBackdrops = movieImageBackdropData?.map { it.toMovieImageBackdrop() }.orEmpty()
)

private fun MovieImageBackdropData.toMovieImageBackdrop() = MovieImageBackdrop(
    filePath = filePath.orEmpty()
)