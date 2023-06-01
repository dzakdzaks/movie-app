package com.dzakdzaks.movieapp.common.model.movie.image

import com.google.gson.annotations.SerializedName

data class MovieImageBackdrop(
    @SerializedName("file_path")
    val filePath: String = ""
) {
    fun getFullBackdropPath(): String = "https://image.tmdb.org/t/p/original$filePath"
}