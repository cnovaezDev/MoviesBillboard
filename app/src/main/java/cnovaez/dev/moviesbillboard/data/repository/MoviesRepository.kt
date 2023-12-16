package cnovaez.dev.moviesbillboard.data.repository

import android.content.Context
import cnovaez.dev.moviesbillboard.data.network.MoviesApi
import cnovaez.dev.moviesbillboard.domain.database.dao.DirectorsDao
import cnovaez.dev.moviesbillboard.domain.database.dao.GenresDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieDirectorCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieGenreCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieStarCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MoviesDao
import cnovaez.dev.moviesbillboard.domain.database.dao.StarsDao
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieDirectorCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieGenreCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieStarCrossRefEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.domain.models.Movie
import cnovaez.dev.moviesbillboard.utils.isDeviceIsConnectedToTheInternet
import javax.inject.Inject

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:10
 ** cnovaez.dev@outlook.com
 **/
class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val genresDao: GenresDao,
    private val directorsDao: DirectorsDao,
    private val starsDao: StarsDao,
    private val movieGenreCrossRefDao: MovieGenreCrossRefDao,
    private val movieDirectorCrossRefDao: MovieDirectorCrossRefDao,
    private val movieStarCrossRefDao: MovieStarCrossRefDao,
    private val context: Context
) {

    suspend fun getAllMovies(): List<MovieWithDetails> {
        val movies = mutableListOf<MovieWithDetails>()
        try {
            val result = mutableListOf<Movie>()
            if (context.isDeviceIsConnectedToTheInternet()) {
                result.addAll(getAllMoviesFromApi())
            }
            if (result.isNotEmpty()) {
                movies.addAll(result.map { it.toMovieWithDetails() })
            } else {
                movies.addAll(getAllMoviesFromDb())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return movies
    }

   private suspend fun getAllMoviesFromApi(): List<Movie> {
        val movies = mutableListOf<Movie>()
        try {
            val response = moviesApi.getMovies()
            if (response.errorMessage.isEmpty()) {
                movies.addAll(response.items)
                //TODO change texts to english: Insertar géneros, directores y estrellas
                movies.flatMap { it.genreList }.distinctBy { it.key }.forEach { genre ->
                    genresDao.insertGenre(genre.toEntity())
                }

                movies.flatMap { it.directorList }.distinctBy { it.id }.forEach { director ->
                    directorsDao.insertDirector(director.toDirectorEntity())
                }

                movies.flatMap { it.starList }.distinctBy { it.id }.forEach { star ->
                    starsDao.insertStar(star.toStarEntity())
                }

                // Insertar películas
                movies.forEach { movie ->
                    moviesDao.insertMovie(movie.toEntity())

                    // Establecer relaciones Movie-Genre
                    movie.genreList.forEach { genre ->
                        val movieGenreCrossRef = MovieGenreCrossRefEntity(movie.id, genre.key)
                        movieGenreCrossRefDao.insert(movieGenreCrossRef)
                    }

                    // Establecer relaciones Movie-Director
                    movie.directorList.forEach { director ->
                        val movieDirectorCrossRef =
                            MovieDirectorCrossRefEntity(movie.id, director.id)
                        movieDirectorCrossRefDao.insert(movieDirectorCrossRef)
                    }

                    // Establecer relaciones Movie-Star
                    movie.starList.forEach { star ->
                        val movieStarCrossRef = MovieStarCrossRefEntity(movie.id, star.id)
                        movieStarCrossRefDao.insert(movieStarCrossRef)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return movies
    }

   private  suspend fun getAllMoviesFromDb(): List<MovieWithDetails> {
        val movies = mutableListOf<MovieWithDetails>()
        try {
            movies.addAll(moviesDao.getMovies())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return movies
    }


}