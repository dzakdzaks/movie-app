package com.dzakdzaks.movieapp.common.model.movie.image

import com.google.gson.annotations.SerializedName

data class MovieImage(
    @SerializedName("backdrops")
    val movieImageBackdrops: List<MovieImageBackdrop> = emptyList()
)