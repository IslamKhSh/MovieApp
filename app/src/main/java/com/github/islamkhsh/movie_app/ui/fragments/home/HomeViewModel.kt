package com.github.islamkhsh.movie_app.ui.fragments.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.common.utils.Localization
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import com.github.islamkhsh.movie_app.ui.base.BaseViewModel

class HomeViewModel(app: Application) : BaseViewModel(app) {

    /**
     * list of categories to be displayed in the home fragment.
     * saved in viewModel to keep the instance of expanding.
     */
    var categories: List<MovieCategory>? = null

    /**
     * counter for the number of networking requests in parallel,
     * check its value to stop loading after finishing all requests.
     */
    private var numOfRequests = 0

    /**
     * change the value of language that's stored in shared pref.
     */
    fun changeLang() {

       if (appRepositoryHelper.getUserLanguage() == Constants.ARABIC)
            appRepositoryHelper.setUserLanguage(Constants.ENGLISH)
        else
           appRepositoryHelper.setUserLanguage(Constants.ARABIC)
    }

    /** get liveData of popular movies as pagedList **/
    fun getPopularMoviesList() = appRepositoryHelper.getPopularMoviesList()

    /**
     * handle the response of popular movies request.
     */
    fun getPopularMoviesApiResponse() {

        numOfRequests++
        isLoading.value = true

        val popularMoviesApiResponse =
            appRepositoryHelper.getPopularMoviesApiResponse()

        errorResponse.removeSource(popularMoviesApiResponse)
        errorResponse.addSource(popularMoviesApiResponse) {

            if (numOfRequests > 0)
                numOfRequests--

            if (numOfRequests == 0)
                isLoading.value = false

            errorResponse.value = it
        }
    }

    /** get liveData of topRated movies as pagedList **/
    fun getTopRatedMoviesList() = appRepositoryHelper.getTopRatedMoviesList()

    /**
     * handle the response of topRated movies request.
     */
    fun getTopRatedMoviesApiResponse(){

        numOfRequests++
        isLoading.value = true

        val topRatedMoviesApiResponse =
            appRepositoryHelper.getTopRatedMoviesApiResponse()


        errorResponse.removeSource(topRatedMoviesApiResponse)
        errorResponse.addSource(topRatedMoviesApiResponse) {

            if (numOfRequests > 0)
                numOfRequests--

            if (numOfRequests == 0)
                isLoading.value = false

            errorResponse.value = it
        }
    }
}