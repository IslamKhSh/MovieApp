package com.github.islamkhsh.movie_app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.islamkhsh.movie_app.di.component.DaggerAppComponent
import com.github.islamkhsh.movie_app.di.module.AppModule

class MovieApp : Application() {

    val component by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}