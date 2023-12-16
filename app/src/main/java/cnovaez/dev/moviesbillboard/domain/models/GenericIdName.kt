package cnovaez.dev.moviesbillboard.domain.models

import cnovaez.dev.moviesbillboard.domain.database.entities.DirectorEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.StarEntity

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:41
 ** cnovaez.dev@outlook.com
 **/
data class GenericIdName(
    val id: String,
    val name: String
) {
    fun toStarEntity() = StarEntity(id, name)
    fun toDirectorEntity() = DirectorEntity( id, name)
}