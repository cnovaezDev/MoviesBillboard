package cnovaez.dev.moviesbillboard.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieEntity
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:47
 ** cnovaez.dev@outlook.com
 **/
@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovie(movieId: String): MovieWithDetails

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieWithDetails>

    @Query("SELECT * FROM movies where id = :movieId")
    suspend fun getMovieById(movieId: String): MovieWithDetails?

    @Query("SELECT * FROM movies where title LIKE '%' || :filter || '%' OR releaseState LIKE '%' || :filter || '%' OR imDbRating LIKE '%' || :filter || '%'")
    suspend fun getMovieByFilter(filter: String): List<MovieWithDetails>

}