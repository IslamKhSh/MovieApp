package com.github.islamkhsh.movie_app.data.network

import com.github.islamkhsh.movie_app.data.network.entities.Movie
import com.github.islamkhsh.movie_app.data.network.entities.MovieDetails
import com.github.islamkhsh.movie_app.data.network.entities.MovieReviews
import com.github.islamkhsh.movie_app.data.network.entities.MovieVideos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page : Int) : Call<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page : Int) : Call<Movie>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") lang : String
    ) : Call<MovieDetails>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") movieId: Int) : Call<MovieVideos>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: Int) : Call<MovieReviews>
}