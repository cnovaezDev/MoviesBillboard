package cnovaez.dev.moviesbillboard.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cnovaez.dev.moviesbillboard.domain.database.entities.GenreEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:38
 ** cnovaez.dev@outlook.com
 **/
data class Genre(
    val key: String,
    val value: String
) {
    fun toEntity() = GenreEntity(key, value)
}