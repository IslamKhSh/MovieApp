package com.github.islamkhsh.movie_app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.common.extensions.appComponent
import com.github.islamkhsh.movie_app.common.extensions.get
import com.github.islamkhsh.movie_app.common.extensions.put
import com.github.islamkhsh.movie_app.common.utils.Localization

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setInitialLanguage()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * check for the stored language in shared pref :
     * - if NOT_DEFINED_LANG : no stored language and set app local to "en"
     * - else update the app locale to match the stored language.
     */
    private fun setInitialLanguage() {
        var currentLang = appComponent().getSharedPreference().get(
            Constants.CURRENT_LANGUAGE_KEY, Constants.NOT_DEFINED_LANG
        )

        if (currentLang == Constants.NOT_DEFINED_LANG) {
            currentLang = Constants.ENGLISH
            appComponent().getSharedPreference().put(Constants.CURRENT_LANGUAGE_KEY, currentLang)
        }

        Localization.setLanguage(this, currentLang)
    }

}
