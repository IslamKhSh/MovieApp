package com.github.islamkhsh.movie_app.data.network.paging

import androidx.paging.DataSource
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import retrofit2.Call


class PagingDataSourceFactory(call: (Int)-> Call<Movie>) : DataSource.Factory<Int, Movie.Result>() {

    private val pagingDataSource = PagingDataSource(call)

    override fun create() = pagingDataSource

    fun getPagingApiResponse()
            = pagingDataSource.pagingApiResponse
}