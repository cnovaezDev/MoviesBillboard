package cnovaez.dev.moviesbillboard.ui

import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 22:09
 ** cnovaez.dev@outlook.com
 **/
sealed class MainUIState {
    object Loading : MainUIState()
    data class Success(val movies: List<MovieWithDetails>) : MainUIState()
    data class Error(val msg: String) : MainUIState()
}