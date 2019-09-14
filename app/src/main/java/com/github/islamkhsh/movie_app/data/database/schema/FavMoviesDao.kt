package com.github.islamkhsh.movie_app.data.database.schema

import androidx.room.Dao
import androidx.room.Query
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie

@Dao
interface FavMoviesDao : BaseDao<FavMovie> {

    @Query("SELECT * FROM movies")
    suspend fun getFavMovies(): List<FavMovie>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getFavMovie(movieId: Int) : List<FavMovie>

}