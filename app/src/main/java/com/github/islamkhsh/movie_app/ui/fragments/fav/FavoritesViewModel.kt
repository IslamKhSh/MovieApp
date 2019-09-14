package com.github.islamkhsh.movie_app.ui.fragments.fav

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.islamkhsh.movie_app.common.extensions.remove
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(app: Application) : BaseViewModel(app) {

    /** liveData that hold the list of fav movies **/
    private val favMovies = MutableLiveData<List<FavMovie>> ()

    /**
     * liveData that hold a boolean value that indicate if user has fav movies or not,
     * and its value will be changed when favMovies's value changes
     */
    val isThereFavMovies = MediatorLiveData<Boolean>().apply {
        value = false
        removeSource(favMovies)
        addSource(favMovies){
            it?.run { value = this.isNotEmpty() }
        }
    }

    /**
     * get the stored fav movies from database using coroutines and update favMovies's value with it.
     * @return MutableLiveData<List<FavMovie>> the liveData that hold the favMovies
     */
    fun getFavMovies(): MutableLiveData<List<FavMovie>> {

        viewModelScope.launch {

            isLoading.postValue(true)
            val movies = withContext(Dispatchers.IO) {
                appRepositoryHelper.getAllFavMovies()
            }

            isLoading.postValue(false)
            favMovies.postValue(movies)
        }

        return favMovies
    }


    /**
     * delete movie on specific position from database using coroutines and then update favMovies's
     * value by the new list.
     * @param itemPosition Int the position of item to be deleted
     */
    fun deleteFavMovie(itemPosition : Int) {

        viewModelScope.launch {
            isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                appRepositoryHelper.deleteFavMovie(favMovies.value?.get(itemPosition)!!)

                favMovies.postValue(favMovies.value?.remove(itemPosition))
                isLoading.postValue(false)
            }
        }
    }

}
