package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.GenreEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:48
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface GenresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Query("SELECT * FROM genres WHERE key = :genreKey")
    suspend fun getGenre(genreKey: String): GenreEntity
}