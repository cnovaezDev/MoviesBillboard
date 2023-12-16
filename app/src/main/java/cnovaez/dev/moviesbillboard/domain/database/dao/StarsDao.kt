package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.StarEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:50
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface StarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStar(star: StarEntity)

    @Query("SELECT * FROM stars WHERE id = :starId")
    suspend fun getStar(starId: String): StarEntity?
}