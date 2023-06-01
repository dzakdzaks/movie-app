package com.dzakdzaks.movieappcore.model.movie.video


import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("results")
    val results: List<ResultMovieVideo> = emptyList()
)