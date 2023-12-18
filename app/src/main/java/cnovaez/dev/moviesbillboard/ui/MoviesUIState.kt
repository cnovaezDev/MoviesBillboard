package cnovaez.dev.moviesbillboard.ui

import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 22:09
 ** cnovaez.dev@outlook.com
 **/
sealed class MoviesUIState {
    object Loading : MoviesUIState()
    data class Success(val movies: List<MovieWithDetails>) : MoviesUIState()
    data class Error(val msg: String) : MoviesUIState()
}