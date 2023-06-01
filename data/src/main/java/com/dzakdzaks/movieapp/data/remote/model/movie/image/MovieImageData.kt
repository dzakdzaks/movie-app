package com.dzakdzaks.movieapp.data.remote.model.movie.image


import com.google.gson.annotations.SerializedName

data class MovieImageData(
    @SerializedName("backdrops")
    val movieImageBackdropData: List<MovieImageBackdropData>?
)