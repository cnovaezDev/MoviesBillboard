package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:24
 ** cnovaez.dev@outlook.com
 **/
data class MovieWithDetails(
    @Embedded val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "key",
        associateBy = Junction(MovieGenreCrossRefEntity::class, parentColumn = "movieId", entityColumn = "genreKey")
    )
    val genres: List<GenreEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieDirectorCrossRefEntity::class, parentColumn = "movieId", entityColumn = "directorId")
    )
    val directors: List<DirectorEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieStarCrossRefEntity::class, parentColumn = "movieId", entityColumn = "starId")
    )
    val stars: List<StarEntity>


)
