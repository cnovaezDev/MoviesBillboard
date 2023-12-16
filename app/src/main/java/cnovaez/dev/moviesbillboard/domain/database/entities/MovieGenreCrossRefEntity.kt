package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:42
 ** cnovaez.dev@outlook.com
 **/
@Entity(
    tableName = "movie_genres",
    primaryKeys = ["movieId", "genreKey"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["key"],
            childColumns = ["genreKey"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieGenreCrossRefEntity(
    val movieId: String,
    val genreKey: String
)
