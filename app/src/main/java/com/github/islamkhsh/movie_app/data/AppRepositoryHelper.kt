package com.github.islamkhsh.movie_app.data

import com.github.islamkhsh.movie_app.data.database.AppDatabaseHelper
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.data.network.AppNetworkHelper
import com.github.islamkhsh.movie_app.data.preferences.AppPreferenceHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryHelper @Inject constructor(
    private val networkHelper: AppNetworkHelper,
    private val preferenceHelper : AppPreferenceHelper,
    private val databaseHelper: AppDatabaseHelper) : RepositoryHelper {

    /** networking **/
    override fun getPopularMoviesList() = networkHelper.getPopularMoviesList()
    override fun getPopularMoviesApiResponse() = networkHelper.getPopularMoviesApiResponse()

    override fun getTopRatedMoviesList() = networkHelper.getTopRatedMoviesList()
    override fun getTopRatedMoviesApiResponse() = networkHelper.getTopRatedMoviesApiResponse()

    override fun getMovieDetails(movieId: Int) = networkHelper.getMovieDetails(movieId)
    override fun getMovieVideos(movieId: Int) = networkHelper.getMovieVideos(movieId)
    override fun getMovieReviews(movieId: Int) = networkHelper.getMovieReviews(movieId)


    /** preferences **/
    override fun setUserLanguage(language: Int) = preferenceHelper.setUserLanguage(language)
    override fun getUserLanguage() = preferenceHelper.getUserLanguage()


    /** database **/
    override suspend fun insertFavMovie(favMovie: FavMovie) = databaseHelper.insertFavMovie(favMovie)
    override suspend fun deleteFavMovie(favMovie: FavMovie) = databaseHelper.deleteFavMovie(favMovie)
    override suspend fun getAllFavMovies() = databaseHelper.getAllFavMovies()
    override suspend fun getFavMovieById(movieId: Int) = databaseHelper.getFavMovieById(movieId)
}