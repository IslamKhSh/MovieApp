package com.github.islamkhsh.movie_app.common.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.islamkhsh.movie_app.MovieApp
import com.github.islamkhsh.movie_app.common.Constants
import com.google.android.material.snackbar.Snackbar


fun Activity.goToActivity(activityClass: Class<*>) = this.startActivity(Intent(this, activityClass))


fun Activity.appComponent() = (this.application as MovieApp).component


@SuppressLint("WrongConstant")
fun Activity.errorMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    val snackbar = Snackbar.make(this.window.decorView, msg, duration)
    snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_red_dark))
    val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(getColorCompat(android.R.color.white))
    snackbar.show()

}

