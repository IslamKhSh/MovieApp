package com.github.islamkhsh.movie_app.data.database.schema.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class FavMovie(
    @PrimaryKey
    val id: Int,
    val poster: String,
    val title: String,
    val overview: String
)