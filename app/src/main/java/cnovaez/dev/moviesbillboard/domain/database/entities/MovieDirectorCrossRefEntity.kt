package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:44
 ** cnovaez.dev@outlook.com
 **/
@Entity(
    tableName = "movie_directors",
    primaryKeys = ["movieId", "directorId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DirectorEntity::class,
            parentColumns = ["id"],
            childColumns = ["directorId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieDirectorCrossRefEntity(
    val movieId: String,
    val directorId: String
)
