package com.github.islamkhsh.movie_app.data.network.entities


import com.google.gson.annotations.SerializedName

data class MovieReviews(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("author")
        val author: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("url")
        val url: String
    )
}