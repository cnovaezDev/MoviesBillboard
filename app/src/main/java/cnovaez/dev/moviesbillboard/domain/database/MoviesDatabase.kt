package cnovaez.dev.moviesbillboard.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cnovaez.dev.moviesbillboard.domain.database.dao.DirectorsDao
import cnovaez.dev.moviesbillboard.domain.database.dao.GenresDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieDirectorCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieGenreCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieStarCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MoviesDao
import cnovaez.dev.moviesbillboard.domain.database.dao.StarsDao
import cnovaez.dev.moviesbillboard.domain.database.entities.DirectorEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.GenreEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieDirectorCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieGenreCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieStarCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.StarEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:08
 ** cnovaez.dev@outlook.com
 **/
@Database(
    entities = [MovieEntity::class, GenreEntity::class, DirectorEntity::class, StarEntity::class, MovieGenreCrossRefEntity::class, MovieDirectorCrossRefEntity::class, MovieStarCrossRefEntity::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    abstract fun genreDao(): GenresDao
    abstract fun directorDao(): DirectorsDao
    abstract fun starDao(): StarsDao
    abstract fun movieGenreCrossRefDao(): MovieGenreCrossRefDao
    abstract fun movieDirectorCrossRefDao(): MovieDirectorCrossRefDao
    abstract fun movieStarCrossRefDao(): MovieStarCrossRefDao
}