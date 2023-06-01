package com.dzakdzaks.movieapp.common.model.movie


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int = -1,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String = "",
    @ColumnInfo(name = "posterPath")
    @SerializedName("poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "releaseDate")
    @SerializedName("release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String = "",
    @ColumnInfo(name = "voteAverage")
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = "page")
    var page: Int,
) {
    fun getFullPosterPath(): String = "https://image.tmdb.org/t/p/w200$posterPath"
}