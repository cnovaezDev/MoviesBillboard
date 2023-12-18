package cnovaez.dev.moviesbillboard.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.ui.MainActivity
import cnovaez.dev.moviesbillboard.ui.components.MovieDetails
import cnovaez.dev.moviesbillboard.ui.components.MovieList
import cnovaez.dev.moviesbillboard.utils.constants.TestsConstants
import cnovaez.dev.moviesbillboard.utils.ext.dummyMovieData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 14:46
 ** cnovaez.dev@outlook.com
 **/
@RunWith(AndroidJUnit4::class)
class UITests {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var fakeMovie: MovieWithDetails
    private lateinit var activity: MainActivity

    @Before
    fun setup() {

        fakeMovie = MovieWithDetails(
            movie = dummyMovieData(),
            emptyList(),
            emptyList(),
            emptyList()
        )

    }

    @Test
    fun selecting_a_movie_item_will_display_his_details() {
        //Arrange
        val viewModel = FakeMovieViewModel()
        val moviesWithId =
            listOf(
                MovieWithDetails(
                    movie = dummyMovieData("1"),
                    emptyList(),
                    emptyList(),
                    emptyList()
                ),
                MovieWithDetails(
                    movie = dummyMovieData("2"),
                    emptyList(),
                    emptyList(),
                    emptyList()
                ),
                MovieWithDetails(
                    movie = dummyMovieData("3"),
                    emptyList(),
                    emptyList(),
                    emptyList()
                ),
            )

        //Arrange
        composeRule.setContent {
            MaterialTheme {
                Surface {
                    val showDetails by viewModel.details.observeAsState(false)
                    MovieList(
                        movies = moviesWithId,
                        onMovieSelected = {},
                        onShowDetailsPressed = {}
                    )

                    if (showDetails) {
                        MovieDetails(
                            movie = MovieWithDetails(
                                movie = dummyMovieData(),
                                emptyList(),
                                emptyList(),
                                emptyList()
                            ),
                            onDismiss = {  },
                            onNavigateToDetailsPressed = { },
                            activity = null
                        )
                    }
                }
            }
        }
        composeRule.onNodeWithTag(TestsConstants.MOVIE_ITEM + "2").performClick()
        viewModel.showDetails()
        //Assert
        composeRule.onNodeWithTag(TestsConstants.MOVIE_DETAILS).assertIsDisplayed()


    }

    @Test
    fun movie_list_is_showing_the_loaded_list_of_movies() {
        //Arrange
        val movies = listOf(
            fakeMovie,
            fakeMovie,
            fakeMovie,
        )

        //Act
        composeRule.setContent {
            MaterialTheme {
                Surface {
                    MovieList(
                        movies = movies,
                        onMovieSelected = {},
                        onShowDetailsPressed = {}
                    )
                }
            }
        }

        //Assert
        composeRule.onNodeWithTag(TestsConstants.MOVIE_LIST).assertIsDisplayed()

    }
}
