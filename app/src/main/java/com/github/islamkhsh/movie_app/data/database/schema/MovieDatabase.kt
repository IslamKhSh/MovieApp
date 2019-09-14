package com.github.islamkhsh.movie_app.data.database.schema

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie

@Database(entities = [FavMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun favMovieDao(): FavMoviesDao

}