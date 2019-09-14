package com.github.islamkhsh.movie_app.ui.fragments.home

import android.os.Bundle
import com.github.islamkhsh.movie_app.ui.base.BaseView

interface HomeView : BaseView {

    /**
     * init clicks listeners of toolbar views.
     */
    fun initHomeToolbar()

    /**
     * init the categories list and pass it to recyclerView's adapter.
     */
    fun initCategories()

    /**
     * if popular adapter already has items restore its position
     * else get the pagedList of popular movies and observe to any errors.
     */
    fun getPopularMovies(savedInstanceState: Bundle?)

    /**
     * if topRated adapter already has items restore its position
     * else get the pagedList of topRated movies and observe to any errors.
     */
    fun getTopRatedMovies(savedInstanceState: Bundle?)

    /**
     * called when user clicks on a movie to open a screen of its details.
     *
     * @param movieId Int the id of the selected movie.
     * @param movieTitle String the title of the selected movie.
     */
    fun setOnMovieSelected(movieId: Int, movieTitle : String)
}