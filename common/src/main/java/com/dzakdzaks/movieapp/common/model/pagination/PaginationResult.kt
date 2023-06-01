package com.dzakdzaks.movieapp.common.model.pagination


import com.google.gson.annotations.SerializedName

data class PaginationResult<T>(
    @SerializedName("dates")
    val datesData: Dates = Dates(),
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("results")
    val results: List<T> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
)