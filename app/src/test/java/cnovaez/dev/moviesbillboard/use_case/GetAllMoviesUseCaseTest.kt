package cnovaez.dev.moviesbillboard.use_case

import android.content.Context
import cnovaez.dev.moviesbillboard.data.network.MoviesApi
import cnovaez.dev.moviesbillboard.data.repository.MoviesRepository
import cnovaez.dev.moviesbillboard.domain.database.dao.DirectorsDao
import cnovaez.dev.moviesbillboard.domain.database.dao.GenresDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieDirectorCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieGenreCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MovieStarCrossRefDao
import cnovaez.dev.moviesbillboard.domain.database.dao.MoviesDao
import cnovaez.dev.moviesbillboard.domain.database.dao.StarsDao
import cnovaez.dev.moviesbillboard.domain.network_response.MoviesResponse
import cnovaez.dev.moviesbillboard.utils.ext.isDeviceIsConnectedToTheInternet
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Carlos A. Novaez Guerrero on 18/12/2023 13:33
 * cnovaez.dev@outlook.com
 */
class GetAllMoviesUseCaseTest {

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getAllMoviesUseCase: GetAllMoviesUseCase

    private lateinit var context: Context

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllMoviesUseCase = GetAllMoviesUseCase(moviesRepository)
        context = mockk()
    }


    @Test
    fun `when API does not return movies, then attempt to load them from the database`() =
        runBlocking {
            // Arrange
            val moviesApi = mockk<MoviesApi>()
            val moviesDao = mockk<MoviesDao>()
            val genresDao = mockk<GenresDao>()
            val directorsDao = mockk<DirectorsDao>()
            val starsDao = mockk<StarsDao>()
            val movieGenreCrossRefDao = mockk<MovieGenreCrossRefDao>()
            val movieDirectorCrossRefDao = mockk<MovieDirectorCrossRefDao>()
            val movieStarCrossRefDao = mockk<MovieStarCrossRefDao>()
            val context = mockk<Context>()

            val apiResponse = mockk<MoviesResponse>()

            val moviesRepository = MoviesRepository(
                moviesApi, moviesDao, genresDao, directorsDao, starsDao,
                movieGenreCrossRefDao, movieDirectorCrossRefDao, movieStarCrossRefDao, context
            )

            coEvery { context.isDeviceIsConnectedToTheInternet() } returns true
            coEvery { moviesApi.getMovies() } returns apiResponse
            coEvery { apiResponse.items } returns emptyList()
            coEvery { apiResponse.errorMessage } returns ""
            coEvery { moviesRepository.getAllMoviesFromApi() } returns null

            val getAllMoviesUseCase = GetAllMoviesUseCase(moviesRepository)

            // Act
            getAllMoviesUseCase()

            // Assert
            coVerify { moviesRepository.getAllMoviesFromDb() }
        }


    @Test
    fun `when no internet, attempt to load movies from the database`() = runBlocking {
        // Arrange
        val moviesApi = mockk<MoviesApi>()
        val moviesDao = mockk<MoviesDao>()
        val genresDao = mockk<GenresDao>()
        val directorsDao = mockk<DirectorsDao>()
        val starsDao = mockk<StarsDao>()
        val movieGenreCrossRefDao = mockk<MovieGenreCrossRefDao>()
        val movieDirectorCrossRefDao = mockk<MovieDirectorCrossRefDao>()
        val movieStarCrossRefDao = mockk<MovieStarCrossRefDao>()
        val context = mockk<Context>()

        val moviesRepository = MoviesRepository(
            moviesApi, moviesDao, genresDao, directorsDao, starsDao,
            movieGenreCrossRefDao, movieDirectorCrossRefDao, movieStarCrossRefDao, context
        )

        coEvery { context.isDeviceIsConnectedToTheInternet() } returns false
        coEvery { moviesDao.getMovies() } returns listOf(/*movies*/)

        val getAllMoviesUseCase = GetAllMoviesUseCase(moviesRepository)

        // Act
        getAllMoviesUseCase()

        // Assert
        coVerify(exactly = 0) { moviesRepository.getAllMoviesFromApi() }
        coVerify(exactly = 1) { moviesRepository.getAllMoviesFromDb() }

    }

}