package cnovaez.dev.moviesbillboard.domain.models

import cnovaez.dev.moviesbillboard.domain.database.entities.MovieEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:37
 ** cnovaez.dev@outlook.com
 **/
data class Movie(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val releaseState: String,
    val image: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val plot: String,
    val contentRating: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val metacriticRating: String,
    val genres: String,
    val genreList: List<Genre>,
    val directors: String,
    val directorList: List<GenericIdName>,
    val stars: String,
    val starList: List<GenericIdName>
) {
    fun toEntity() = MovieEntity(
        id,
        title,
        fullTitle,
        year,
        releaseState,
        image,
        runtimeMins,
        runtimeStr,
        plot,
        contentRating,
        imDbRating,
        imDbRatingCount,
        metacriticRating,
    )

    fun toMovieWithDetails() =  MovieWithDetails(
            MovieEntity(
                id,
                title,
                fullTitle,
                year,
                releaseState,
                image,
                runtimeMins,
                runtimeStr,
                plot,
                contentRating,
                imDbRating,
                imDbRatingCount,
                metacriticRating,
            ),
            genreList.map { it.toEntity() },
            directorList.map { it.toDirectorEntity() },
            starList.map { it.toStarEntity() }
        )

}