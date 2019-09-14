package com.github.islamkhsh.movie_app.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.github.islamkhsh.movie_app.data.network.entities.*
import okhttp3.ResponseBody

interface NetworkHelper {

    /**
     * use this method to return popular movies list as PagedList
     * @return LiveData<PagedList<Movie.Result>>
     */
    fun getPopularMoviesList(): LiveData<PagedList<Movie.Result>>

    /**
     * use this method to get the popular movies endpoint response in all cases
     * (failure, error, succeed)
     * @return MutableLiveData<ApiResponse<ResponseBody>>
     */
    fun getPopularMoviesApiResponse(): MutableLiveData<ApiResponse<ResponseBody>>

    /**
     * use this method to return topRated movies list as PagedList
     * @return LiveData<PagedList<Movie.Result>>
     */
    fun getTopRatedMoviesList(): LiveData<PagedList<Movie.Result>>

    /**
     * use this method to get the topRated movies endpoint response in all cases
     * (failure, error, succeed)
     * * @return MutableLiveData<ApiResponse<ResponseBody>>
     */
    fun getTopRatedMoviesApiResponse(): MutableLiveData<ApiResponse<ResponseBody>>

    /**
     * use this to get movie details by its id.
     * @param movieId Int the id of the movie to get its details.
     * @return MutableLiveData<ApiResponse<MovieDetails>>
     */
    fun getMovieDetails(movieId : Int) : MutableLiveData<ApiResponse<MovieDetails>>

    /**
     * use this to get movie videos by its id.
     * @param movieId Int the id of the movie to get its videos.
     * @return MutableLiveData<ApiResponse<MovieVideos>>
     */
    fun getMovieVideos(movieId : Int) : MutableLiveData<ApiResponse<MovieVideos>>

    /**
     * use this to get movie reviews by its id.
     * @param movieId Int the id of the movie to get its reviews.
     * @return MutableLiveData<ApiResponse<MovieReviews>>
     */
    fun getMovieReviews(movieId : Int) : MutableLiveData<ApiResponse<MovieReviews>>
}