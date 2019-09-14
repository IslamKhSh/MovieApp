package com.github.islamkhsh.movie_app.ui.fragments.fav

import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.ui.base.BaseView

interface FavoritesView : BaseView {

    /**
     * use this method to get stored movies from database
     */
    fun getFavMovies()

    /**
     * display the fav movies and handle the swipe to delete action
     * @param favMovies List<FavMovie> list of favMovies
     */
    fun initFavMoviesRecycler(favMovies: List<FavMovie>)

    /**
     * called when user clicks on a movie to open a screen of its details.
     *
     * @param movieId Int the id of the selected movie.
     * @param movieTitle String the title of the selected movie.
     */
    fun setOnMovieSelected(movieId: Int, movieTitle : String)

}