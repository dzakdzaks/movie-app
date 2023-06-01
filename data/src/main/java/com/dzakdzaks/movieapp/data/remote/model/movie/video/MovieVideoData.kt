package com.dzakdzaks.movieapp.data.remote.model.movie.video


import com.google.gson.annotations.SerializedName

data class MovieVideoData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<ResultMovieVideoData>?
)