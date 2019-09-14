package com.github.islamkhsh.movie_app.common.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import com.github.islamkhsh.movie_app.common.Constants
import java.util.*

/**
 * Created by ESLAM on 8/17/2018.
 */

object Localization {


    fun setLanguage(context: Context, lang: Int) {

        val mLocale: Locale = if (lang == Constants.ARABIC)
            Locale("ar")
        else
            Locale("en")

        Locale.setDefault(mLocale)
        val config = context.resources.configuration

        if (config.locale != mLocale) {
            config.locale = mLocale
            config.setLayoutDirection(config.locale)
            context.resources.updateConfiguration(config, null)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                (context as Activity).window
                    .decorView.layoutDirection =
                    if (Locale.getDefault().language.equals("ar", ignoreCase = true))
                        View.LAYOUT_DIRECTION_RTL
                    else
                        View.LAYOUT_DIRECTION_LTR
            }
        }
    }
}
