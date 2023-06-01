package com.dzakdzaks.movieapp.data.remote.model.movie.video


import com.google.gson.annotations.SerializedName

data class ResultMovieVideoData(
    @SerializedName("id")
    val id: String?,
    @SerializedName("key") //https://www.youtube.com/watch?v=yjRHZEUamCc
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)