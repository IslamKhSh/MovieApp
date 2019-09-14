package com.github.islamkhsh.movie_app.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.paging.PagedList
import com.github.islamkhsh.movie_app.MovieApp
import com.github.islamkhsh.movie_app.common.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies for tha entire app
 */
@Module
class AppModule(val application: MovieApp) {

    /**
     * Provides App instance of the application
     *
     * @return the instance of application
     */
    @Provides
    @Singleton
    fun provideApplication() = application

    /**
     * provide context to be used in data repository
     *
     * @return applicationContext
     */
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    /**
     * provide shared preference to store data
     *
     * @return shared preference instance
     */
    @Provides
    @Singleton
    fun provideSharedPref() = application.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)


    /**
     * use this method to provide configuration of paged list for paging
     *
     * @return (android.arch.paging.PagedList.Config..android.arch.paging.PagedList.Config?)
     */
    @Provides
    @Singleton
    fun providePagedListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(Constants.INITIAL_LOAD_SIZE_HINT)
        .setPageSize(Constants.ROWS_NUM)
        .build()
}