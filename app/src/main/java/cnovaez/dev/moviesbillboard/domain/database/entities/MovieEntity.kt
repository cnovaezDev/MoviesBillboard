package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:37
 ** cnovaez.dev@outlook.com
 **/
@Entity(tableName = "movies", primaryKeys = ["id"])
data class MovieEntity(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val metacriticRating: String,
)