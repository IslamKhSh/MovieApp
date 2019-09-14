package com.github.islamkhsh.movie_app.data.network.entities

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    val errors: Int,
    @SerializedName("status_message")
    val message: String
)