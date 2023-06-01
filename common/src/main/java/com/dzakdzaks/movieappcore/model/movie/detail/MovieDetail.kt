package com.dzakdzaks.movieappcore.model.movie.detail


import com.google.gson.annotations.SerializedName
import java.math.RoundingMode
import java.text.DecimalFormat

data class MovieDetail(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genres")
    val genres: List<Genre> = emptyList(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("runtime")
    val runtime: Int = 0,
) {
    fun getFullPosterPath(): String = "https://image.tmdb.org/t/p/original$posterPath"

    fun isTitleAndOriginalTitleSame(): Boolean = title == originalTitle

    fun getTaglineWithQuotation(): String = if (tagline.isNotEmpty()) "\"${tagline}\"" else ""

    fun getYearOfReleaseDate(): String = releaseDate.split("-")[0]

    fun getReadableRuntime(): String {
        val hours = runtime / 60
        val minutesInHour = runtime % 60

        return "${hours}h ${minutesInHour}m"
    }

    fun getVoteRound(): String {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return "${df.format(voteAverage).toDouble()}/10"
    }
}