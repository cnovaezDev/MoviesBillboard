package cnovaez.dev.moviesbillboard.domain.models

import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 16:43
 ** cnovaez.dev@outlook.com
 **/
data class MovieDataResponse (
    val movies: List<MovieWithDetails>,
    var errorMessage: String = ""
)