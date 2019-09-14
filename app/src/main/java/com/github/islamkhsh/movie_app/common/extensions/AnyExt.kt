package com.github.islamkhsh.movie_app.common.extensions

import android.util.Log

fun Any.logE(message: String) = Log.e(this::class.java.simpleName, message)

fun Any.logE(t: Throwable) = Log.e(this::class.java.simpleName, t.message, t)

fun Any.logD(message: String) = Log.d(this::class.java.simpleName, message)

fun Any.logV(message: String) = Log.v(this::class.java.simpleName, message)

fun Any.logW(message: String) = Log.w(this::class.java.simpleName, message)

fun Any.logI(message: String) = Log.i(this::class.java.simpleName, message)

