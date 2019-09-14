package com.github.islamkhsh.movie_app.data.database

import com.github.islamkhsh.movie_app.data.database.schema.MovieDatabase
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import javax.inject.Inject

class AppDatabaseHelper @Inject constructor(private val db: MovieDatabase) : DatabaseHelper {

    override suspend fun insertFavMovie(favMovie: FavMovie) =
        db.favMovieDao().insert(favMovie)

    override suspend fun getAllFavMovies() = db.favMovieDao().getFavMovies()

    override suspend fun getFavMovieById(movieId: Int) =
        db.favMovieDao().getFavMovie(movieId)

    override suspend fun deleteFavMovie(favMovie: FavMovie) =
        db.favMovieDao().delete(favMovie)
}