package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.GenreEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieGenreCrossRefEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:31
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface MovieGenreCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieGenreCrossRef: MovieGenreCrossRefEntity)

//    @Query("SELECT * FROM movie_genres WHERE movieId = :movieId")
//    suspend fun getGenresForMovie(movieId: String): List<GenreEntity>
}