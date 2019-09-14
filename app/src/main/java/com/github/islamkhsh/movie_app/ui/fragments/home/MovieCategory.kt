package com.github.islamkhsh.movie_app.ui.fragments.home

import com.github.islamkhsh.movie_app.ui.adapters.MoviesPagedAdapter

class MovieCategory (val categoryName : Int,
                     val moviesPagedAdapter: MoviesPagedAdapter,
                     var isExpended : Boolean = false)
