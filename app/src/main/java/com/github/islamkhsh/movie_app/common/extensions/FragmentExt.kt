package com.github.islamkhsh.movie_app.common.extensions

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.github.islamkhsh.movie_app.MovieApp
import com.github.islamkhsh.movie_app.common.Constants
import com.google.android.material.snackbar.Snackbar


fun Fragment.getColorCompat(@ColorRes id: Int) = context!!.getColorCompat(id)

fun Fragment.goToActivity(activityClass: Class<*>) = activity?.goToActivity(activityClass)


@SuppressLint("WrongConstant")
fun Fragment.errorMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    view?.run {
        val snackbar = Snackbar.make(this, msg, duration)
        snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_red_dark))
        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(getColorCompat(android.R.color.white))
        snackbar.show()
    }
}


fun Fragment.errorMsg(@StringRes msgId: Int, duration: Int = Constants.SNAK_BAR_DURATION) {
    errorMsg(getString(msgId), duration)
}
