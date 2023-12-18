package cnovaez.dev.moviesbillboard.domain.models

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 19:22
 ** cnovaez.dev@outlook.com
 **/

sealed class Routes(val route: String) {
    object MoviesList : Routes("movie_list")
    object MovieFullDetails : Routes("movie_full_details/{movieId}") {
        fun createRoute(movieId: String) = "movie_full_details/$movieId"
    }
}