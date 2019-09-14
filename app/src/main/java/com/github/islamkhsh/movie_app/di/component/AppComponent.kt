package com.github.islamkhsh.movie_app.di.component

import android.content.Context
import android.content.SharedPreferences
import com.github.islamkhsh.movie_app.di.module.AppModule
import com.github.islamkhsh.movie_app.di.module.NetworkModule
import com.github.islamkhsh.movie_app.MovieApp
import com.github.islamkhsh.movie_app.di.module.DatabaseModule
import com.github.islamkhsh.movie_app.ui.base.BaseView
import com.github.islamkhsh.movie_app.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun app(): MovieApp
    fun context(): Context

    fun inject(baseViewModel: BaseViewModel)

    fun getSharedPreference(): SharedPreferences
}