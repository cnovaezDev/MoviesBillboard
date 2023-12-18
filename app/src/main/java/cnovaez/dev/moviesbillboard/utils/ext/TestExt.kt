package cnovaez.dev.moviesbillboard.utils.ext

import cnovaez.dev.moviesbillboard.domain.database.entities.MovieEntity
import cnovaez.dev.moviesbillboard.ui.components.MovieDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 18/12/2023 16:13
 ** cnovaez.dev@outlook.com
 **/
fun dummyMovieData(id: String = "1") = MovieEntity(
    id,
    "Mocked Movie Title",
    "2023-01-01",
    "Mocked Movie Overview",
    "Mocked Movie Poster Path",
    "Mocked Movie Backdrop Path",
    "0.0",
    "0",
    "Something",
    "0",
    "0",
    "0",
    "0",
)