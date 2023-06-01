package com.dzakdzaks.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzakdzaks.movieapp.common.model.movie.Movie
import com.dzakdzaks.movieapp.common.model.movie.RemoteKeys

@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}