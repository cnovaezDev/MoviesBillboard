package cnovaez.dev.moviesbillboard.use_case

import android.content.Context
import cnovaez.dev.moviesbillboard.data.repository.MoviesRepository
import cnovaez.dev.moviesbillboard.domain.database.dao.DirectorsDao
import cnovaez.dev.moviesbillboard.domain.database.dao.GenresDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieDirectorCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieGenreCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieStarCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MoviesDao
import cnovaez.dev.moviesbillboard.domain.database.dao.StarsDao
import cnovaez.dev.moviesbillboard.domain.models.MovieDataResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Carlos A. Novaez Guerrero on 18/12/2023 13:56
 * cnovaez.dev@outlook.com
 */
class GetAllFilteredMoviesUseCaseTest {
    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getAllFilteredMoviesUseCase: GetAllFilteredMoviesUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllFilteredMoviesUseCase = GetAllFilteredMoviesUseCase(moviesRepository)
    }

    @Test
    fun `invoke with non-empty filter should return filtered movies`() = runBlocking {
        // Arrange
        val filter = "Action"
        val expectedResponse =
            MovieDataResponse(listOf(/* Lista de películas filtradas por 'Action' */))

        coEvery { moviesRepository.getAllMoviesFromDbFiltered(filter) } returns expectedResponse

        // Act
        val result = getAllFilteredMoviesUseCase(filter)

        // Assert
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `if filter is not empty should return the filtered movie list`() = runBlocking {
        // Arrange
        val moviesDao = mockk<MoviesDao>()
        val genresDao = mockk<GenresDao>()
        val directorsDao = mockk<DirectorsDao>()
        val starsDao = mockk<StarsDao>()
        val movieGenreCrossRefDao = mockk<MovieGenreCrossRefDao>()
        val movieDirectorCrossRefDao = mockk<MovieDirectorCrossRefDao>()
        val movieStarCrossRefDao = mockk<MovieStarCrossRefDao>()
        val context = mockk<Context>()

        val moviesRepository = MoviesRepository(
            mockk(), moviesDao, genresDao, directorsDao, starsDao,
            movieGenreCrossRefDao, movieDirectorCrossRefDao, movieStarCrossRefDao, context
        )

        val filter = "Action"

        coEvery { moviesDao.getMovieByFilter(filter) } returns listOf(/* movie list */)

        // Act
        moviesRepository.getAllMoviesFromDbFiltered(filter)

        // Assert
        coVerify (exactly = 1) { moviesDao.getMovieByFilter(filter) }
    }
    @Test
    fun `if filter is empty should return the all the movies`() = runBlocking {
        // Arrange
        val moviesDao = mockk<MoviesDao>()
        val genresDao = mockk<GenresDao>()
        val directorsDao = mockk<DirectorsDao>()
        val starsDao = mockk<StarsDao>()
        val movieGenreCrossRefDao = mockk<MovieGenreCrossRefDao>()
        val movieDirectorCrossRefDao = mockk<MovieDirectorCrossRefDao>()
        val movieStarCrossRefDao = mockk<MovieStarCrossRefDao>()
        val context = mockk<Context>()

        val moviesRepository = MoviesRepository(
            mockk(), moviesDao, genresDao, directorsDao, starsDao,
            movieGenreCrossRefDao, movieDirectorCrossRefDao, movieStarCrossRefDao, context
        )

        val filter = ""

        coEvery { moviesDao.getMovies() } returns listOf(/* movie list */)

        // Act
        moviesRepository.getAllMoviesFromDbFiltered(filter)

        // Assert
        coVerify (exactly = 0) { moviesDao.getMovieByFilter(filter) }
        coVerify (exactly = 1) { moviesDao.getMovies() }
    }

}