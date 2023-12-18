package cnovaez.dev.moviesbillboard.data.repository

import android.content.Context
import android.util.Log
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
import cnovaez.dev.moviesbillboard.domain.models.MovieDataResponse
import cnovaez.dev.moviesbillboard.utils.ext.isDeviceIsConnectedToTheInternet
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

    suspend fun getAllMovies(): MovieDataResponse? {
        var response: MovieDataResponse? = null
        try {
            if (context.isDeviceIsConnectedToTheInternet()) {
                response = getAllMoviesFromApi()
            }
            if (response == null || response.movies.isEmpty()) {
                response = getAllMoviesFromDb()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            response = MovieDataResponse(emptyList(), e.message ?: "Error unknown")
        }
        return response
    }

    private suspend fun getAllMoviesFromApi(): MovieDataResponse? {
        var response: MovieDataResponse? = null
        try {
            val apiResponse = moviesApi.getMovies()
            if (apiResponse.errorMessage.isEmpty() && apiResponse.items.isNotEmpty()) {
                val movies = apiResponse.items
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
                    response = MovieDataResponse(movies.map { it.toMovieWithDetails() })
                }
            } else {
                response = MovieDataResponse(emptyList(), apiResponse.errorMessage)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            response = MovieDataResponse(emptyList(), e.message ?: "Error unknown")
        }
        return response
    }

    private suspend fun getAllMoviesFromDb(): MovieDataResponse? {
        Log.i("Calling get all movies from db", "started")
        var response: MovieDataResponse? = null
        try {
            response = MovieDataResponse(moviesDao.getMovies())
        } catch (e: Exception) {
            e.printStackTrace()
            response = MovieDataResponse(emptyList(), e.message ?: "Error unknown")
        }
        return response
    }

    suspend fun getAllMoviesFromDbFiltered(filter: String): MovieDataResponse? {
        Log.i("Calling getAllMoviesFromDbFiltered", "started")
        var response: MovieDataResponse? = null
        try {
            response =   if(filter.isNotEmpty()){
                MovieDataResponse(moviesDao.getMovieByFilter(filter))
            } else {
                MovieDataResponse(moviesDao.getMovies())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            response = MovieDataResponse(emptyList(), e.message ?: "Error unknown")
        }
        return response
    }

    suspend fun getMovieById(movieId: String): MovieWithDetails? {
        return try {
            moviesDao.getMovieById(movieId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}