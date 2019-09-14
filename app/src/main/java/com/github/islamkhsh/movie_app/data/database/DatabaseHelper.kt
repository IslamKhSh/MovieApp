package com.github.islamkhsh.movie_app.data.database

import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie


interface DatabaseHelper {

    /**
     * use this method to insert a movie as row in movies table
     * @param favMovie FavMovie the movie to be inserted
     */
    suspend fun insertFavMovie(favMovie: FavMovie)

    /**
     * use this method to get all the fav movies stored in the movies table
     * @return List<FavMovie> the list of fav movies
     */
    suspend fun getAllFavMovies() : List<FavMovie>

    /**
     * use this method to get fav movie by its id
     * @param movieId Int the id of movie
     * @return List<FavMovie> list of one item or empty list
     */
    suspend fun getFavMovieById(movieId : Int) : List<FavMovie>

    /**
     * use this method to delete a row from movies table
     * @param favMovie FavMovie the row object to be deleted
     */
    suspend fun deleteFavMovie(favMovie: FavMovie)
}