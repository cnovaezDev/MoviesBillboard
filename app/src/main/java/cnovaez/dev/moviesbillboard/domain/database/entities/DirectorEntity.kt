package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:41
 ** cnovaez.dev@outlook.com
 **/
@Entity(tableName = "directors")
data class DirectorEntity(
    @PrimaryKey
    val id: String,
    val name: String
)