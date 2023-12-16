package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.DirectorEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:49
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface DirectorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: DirectorEntity)

    @Query("SELECT * FROM directors WHERE id = :directorId")
    suspend fun getDirector(directorId: String): DirectorEntity
}