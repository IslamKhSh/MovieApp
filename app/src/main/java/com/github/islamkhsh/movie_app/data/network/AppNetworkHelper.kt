package com.github.islamkhsh.movie_app.data.network

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.common.extensions.get
import com.github.islamkhsh.movie_app.common.extensions.getResponse
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import com.github.islamkhsh.movie_app.data.network.entities.MovieDetails
import com.github.islamkhsh.movie_app.data.network.entities.MovieReviews
import com.github.islamkhsh.movie_app.data.network.entities.MovieVideos
import com.github.islamkhsh.movie_app.data.network.paging.PagingDataSourceFactory
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNetworkHelper @Inject constructor(
    private val apiService: ApiService,
    private val pagedListConfig: PagedList.Config,
    private val preferences: SharedPreferences
) : NetworkHelper {

    private val popularMoviesDataSourceFactory by lazy {
        PagingDataSourceFactory(apiService::getPopularMovies)
    }

    override fun getPopularMoviesApiResponse() =
        popularMoviesDataSourceFactory.getPagingApiResponse()

    override fun getPopularMoviesList() =
        LivePagedListBuilder(popularMoviesDataSourceFactory, pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()


    private val topRatedMoviesDataSourceFactory by lazy {
        PagingDataSourceFactory(apiService::getTopRatedMovies)
    }

    override fun getTopRatedMoviesApiResponse() =
        topRatedMoviesDataSourceFactory.getPagingApiResponse()

    override fun getTopRatedMoviesList() =
        LivePagedListBuilder(topRatedMoviesDataSourceFactory, pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()


    override fun getMovieDetails(movieId: Int): MutableLiveData<ApiResponse<MovieDetails>> {

        val lang =
            if (preferences.getInt(Constants.CURRENT_LANGUAGE_KEY, 0) == Constants.ARABIC) "ar"
            else "en"

        return apiService.getMovieDetails(movieId, lang).getResponse()
    }

    override fun getMovieVideos(movieId: Int) =
        apiService.getMovieVideos(movieId).getResponse()

    override fun getMovieReviews(movieId: Int) =
        apiService.getMovieReviews(movieId).getResponse()
}