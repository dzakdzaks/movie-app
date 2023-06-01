package com.dzakdzaks.movieapp.data.remote.model.pagination


import com.google.gson.annotations.SerializedName

data class PaginationResultData<T>(
    @SerializedName("dates")
    val datesData: DatesData?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<T>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)