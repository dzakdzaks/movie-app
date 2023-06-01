package com.dzakdzaks.movieapp.ui.screen.detail

import com.dzakdzaks.movieappcore.model.movie.detail.MovieDetail
import com.dzakdzaks.movieappcore.model.movie.image.MovieImageBackdrop
import com.dzakdzaks.movieappcore.model.movie.video.ResultMovieVideo

data class DetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val movieDetailLoading: Boolean = true,
    val movieDetailError: String = "",

    val movieTrailers: List<ResultMovieVideo> = emptyList(),
    val movieTrailerLoading: Boolean = true,
    val movieTrailerError: String = "",

    val movieImages: List<MovieImageBackdrop> = emptyList(),
    val movieImageLoading: Boolean = true,
    val movieImageError: String = "",
)