package com.dzakdzaks.movieapp.data.remote.model.movie.image


import com.google.gson.annotations.SerializedName

data class MovieImageBackdropData(
    @SerializedName("file_path")
    val filePath: String?
)