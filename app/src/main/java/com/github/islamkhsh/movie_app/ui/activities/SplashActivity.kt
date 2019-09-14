package com.github.islamkhsh.movie_app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.islamkhsh.movie_app.common.extensions.goToActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToActivity(MainActivity::class.java)
        finish()
    }
}
