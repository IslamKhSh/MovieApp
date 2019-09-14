package com.github.islamkhsh.movie_app.ui.fragments.movie_details

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import com.github.islamkhsh.movie_app.data.network.entities.MovieReviews
import com.github.islamkhsh.movie_app.data.network.entities.MovieVideos
import com.github.islamkhsh.movie_app.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(app: Application) : BaseViewModel(app) {

    /** the id of the movie **/
    var movieId = 0

    /**
     *  variable that hold the object of the stored data if the movie is in fav list or null if not.
     **/
    private var favMovie: FavMovie? = null

    /**
     * counter for the number of networking requests in parallel,
     * check its value to stop loading after finishing all requests.
     */
    private var numOfRequests = 0

    // liveData instances to update UI using dataBinding
    val isDataLoaded = MutableLiveData<Boolean>()
    val bannerUrl = MutableLiveData<String>()
    val posterUrl = MutableLiveData<String>()
    val movieTitle = MutableLiveData<String>()
    val movieOverview = MutableLiveData<String>()
    val movieReleaseDate = MutableLiveData<String>()
    val movieRate = MutableLiveData<Float>()
    val isFavMovie = MutableLiveData<Boolean>().apply { value = false }


    /**
     * get the movieDetails and then if the request done successfully :
     * - update the liveData objects of UI.
     * - check if this movie in database or not.
     * @param movieId Int the id of movie to get its details
     */
    fun getMovieDetails(movieId: Int) {

        this.movieId = movieId

        numOfRequests++
        isLoading.value = true

        val movieDetailsResponse =
            appRepositoryHelper.getMovieDetails(movieId)

        errorResponse.removeSource(movieDetailsResponse)
        errorResponse.addSource(movieDetailsResponse) {

            if (numOfRequests > 0)
                numOfRequests--

            if (numOfRequests == 0)
                isLoading.value = false

            if (it.isResponseSuccessful)
                it.responseBody?.run {
                    isDataLoaded.postValue(true)
                    bannerUrl.postValue(backdropPath)
                    posterUrl.postValue(posterPath)
                    movieTitle.postValue(originalTitle)
                    movieOverview.postValue(overview)
                    movieReleaseDate.postValue(releaseDate)
                    movieRate.postValue(voteAverage)

                    checkIfFavMovie()
                }

            errorResponse.value = it
        }
    }

    /**
     * check if this movie is in fav list (stored in database) and update the value of both :
     * - isFavMovie liveData
     * - favMovie.
     */
    private fun checkIfFavMovie() {
        viewModelScope.launch {

            isLoading.postValue(true)
            val movie = withContext(Dispatchers.IO) {
                appRepositoryHelper.getFavMovieById(movieId)
            }

            isFavMovie.postValue(movie.isNotEmpty())
            isLoading.postValue(false)

            favMovie = if (movie.isNotEmpty()) movie[0] else null
        }
    }

    /**
     * get the movie videos and return a liveData that hold these.
     * @param movieId Int the id of the movie to get its videos.
     * @return MutableLiveData<ApiResponse<MovieVideos>>
     */
    fun getMovieVideos(movieId: Int): MutableLiveData<ApiResponse<MovieVideos>> {

        numOfRequests++
        isLoading.value = true

        val movieVideosResponse =
            appRepositoryHelper.getMovieVideos(movieId)

        errorResponse.removeSource(movieVideosResponse)
        errorResponse.addSource(movieVideosResponse) {

            if (numOfRequests > 0)
                numOfRequests--

            if (numOfRequests == 0)
                isLoading.value = false

            errorResponse.value = it
        }

        return movieVideosResponse
    }

    /**
     * get the movie reviews and return a liveData that hold these.
     * @param movieId Int the id of the movie to get its reviews.
     * @return MutableLiveData<ApiResponse<MovieVideos>>
     */
    fun getMovieReviews(movieId: Int): MutableLiveData<ApiResponse<MovieReviews>> {

        numOfRequests++
        isLoading.value = true

        val movieReviewsResponse =
            appRepositoryHelper.getMovieReviews(movieId)

        errorResponse.removeSource(movieReviewsResponse)
        errorResponse.addSource(movieReviewsResponse) {

            if (numOfRequests > 0)
                numOfRequests--

            if (numOfRequests == 0)
                isLoading.value = false

            errorResponse.value = it
        }

        return movieReviewsResponse
    }

    /**
     * get the content to be share when user click on share.
     * @param context Context
     * @return String the msg that will be shared
     */
    fun getShareMsg(context: Context): String {
        val by = context.getString(R.string.by)
        val appName = context.getString(R.string.app_name)
        return "${movieTitle.value}\n${movieOverview.value}\n\n$by $appName"
    }

    /**
     * favourite fab click listener :
     * - delete movie from database if movie already stored.
     * - insert movie into database if not.
     */
    fun onFabFavClicked() {
        if (isFavMovie.value!! && favMovie != null)
            deleteFavMovie()
        else
            insertFavMovie()
    }

    /**
     * create instance of FavMovie and insert it into database using coroutines,
     * then update the value of both  isFavMovie liveData and favMovie.
     */
    private fun insertFavMovie() {

        viewModelScope.launch {
            isLoading.postValue(true)
            val favMovie =
                FavMovie(movieId, posterUrl.value!!, movieTitle.value!!, movieOverview.value!!)

            withContext(Dispatchers.IO){
                appRepositoryHelper.insertFavMovie(favMovie)
                checkIfFavMovie()
            }
        }
    }

    /**
     * delete this movie from database using coroutines,
     * then update the value of both  isFavMovie liveData and favMovie.
     */
    private fun deleteFavMovie() {

        viewModelScope.launch {
            isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                appRepositoryHelper.deleteFavMovie(favMovie!!)
                checkIfFavMovie()
            }
        }
    }
}