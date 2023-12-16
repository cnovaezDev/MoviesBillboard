package cnovaez.dev.moviesbillboard.domain.database.entities

import androidx.room.Entity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:38
 ** cnovaez.dev@outlook.com
 **/

@Entity(tableName = "genres", primaryKeys = ["key"])
data class GenreEntity(
    val key: String,
    val value: String
)