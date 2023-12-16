package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:45
 ** cnovaez.dev@outlook.com
 **/
@Entity(
    tableName = "movie_stars",
    primaryKeys = ["movieId", "starId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StarEntity::class,
            parentColumns = ["id"],
            childColumns = ["starId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieStarCrossRefEntity(
    val movieId: String,
    val starId: String
)