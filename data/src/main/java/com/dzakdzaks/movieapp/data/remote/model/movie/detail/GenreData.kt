package com.dzakdzaks.movieapp.data.remote.model.movie.detail


import com.google.gson.annotations.SerializedName

data class GenreData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)