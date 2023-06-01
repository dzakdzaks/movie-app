package com.dzakdzaks.movieapp.data.remote.model.movie.review


import com.google.gson.annotations.SerializedName

data class AuthorDetailsData(
    @SerializedName("avatar_path")
    val avatarPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("username")
    val username: String?
)