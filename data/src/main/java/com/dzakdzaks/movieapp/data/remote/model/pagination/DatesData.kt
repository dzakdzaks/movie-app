package com.dzakdzaks.movieapp.data.remote.model.pagination


import com.google.gson.annotations.SerializedName

data class DatesData(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?
)