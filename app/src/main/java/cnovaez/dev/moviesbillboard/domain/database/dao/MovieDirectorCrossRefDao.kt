package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieDirectorCrossRefEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:34
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface MovieDirectorCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDirectorCrossRef: MovieDirectorCrossRefEntity)

}