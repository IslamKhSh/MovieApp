package com.github.islamkhsh.movie_app.common.extensions

fun <T>List<T>.remove(position: Int)  = this.filter { it != this[position] }