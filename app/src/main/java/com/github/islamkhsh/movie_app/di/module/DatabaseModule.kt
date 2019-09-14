package com.github.islamkhsh.movie_app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import androidx.room.Room
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.data.database.schema.MovieDatabase

/**
 * Module which provides all required dependencies about Room Database
 */
@Module
class DatabaseModule {


    /**
     * Provides the database object
     *
     * @param context the context
     * @return the MovieDatabase instance
     */
    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room
        .databaseBuilder(context, MovieDatabase::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
}