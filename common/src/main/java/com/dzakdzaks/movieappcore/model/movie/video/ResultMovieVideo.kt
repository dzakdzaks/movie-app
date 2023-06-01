package com.dzakdzaks.movieappcore.model.movie.video


import com.google.gson.annotations.SerializedName

data class ResultMovieVideo(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("key")
    val key: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = ""
) {
    fun getThumbnail(): String = "http://img.youtube.com/vi/$key/maxresdefault.jpg"

    fun getUrl(): String = "https://www.youtube.com/watch?v=$key"
}