package cnovaez.dev.moviesbillboard.domain.network_response

import cnovaez.dev.moviesbillboard.domain.models.Movie

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:08
 ** cnovaez.dev@outlook.com
 **/
data class MoviesResponse(
    val items: List<Movie>,
    var errorMessage: String
)
