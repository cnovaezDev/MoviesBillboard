package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieStarCrossRefEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:33
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface MovieStarCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieStarCrossRef: MovieStarCrossRefEntity)

}