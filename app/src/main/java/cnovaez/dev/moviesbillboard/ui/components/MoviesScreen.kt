package cnovaez.dev.moviesbillboard.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import cnovaez.dev.moviesbillboard.domain.models.Routes
import cnovaez.dev.moviesbillboard.ui.MainActivity
import cnovaez.dev.moviesbillboard.ui.MoviesUIState
import cnovaez.dev.moviesbillboard.ui.viewmodels.MainActivityViewModel
import cnovaez.dev.moviesbillboard.utils.ext.MODE_DARK
import cnovaez.dev.moviesbillboard.utils.ext.forceAppClose
import cnovaez.dev.moviesbillboard.utils.ext.formatDateComparable
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 22:31
 ** cnovaez.dev@outlook.com
 **/

/**
 * Main screen of the app, where the list of movies and other visual components are shown
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoviesScreen(
    viewModel: MainActivityViewModel,
    navigationController: NavHostController,
    activity: MainActivity
) {
    val context = LocalContext.current
    val nightMode by viewModel.loadMode(context).collectAsState(initial = 0)
    val showDetails = viewModel.showDetails.observeAsState(initial = false)
    val selectedMovie = viewModel.selectedMovie.observeAsState(initial = null)
    val movieUiState = viewModel.movies.value
    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }
    if (firstTime) {
        viewModel.getAllMovies()
        firstTime = false

    }

    var loading by rememberSaveable {
        mutableStateOf(true)
    }

    MaterialTheme(
        colorScheme = if (nightMode == MODE_DARK) darkColorScheme() else lightColorScheme(),
    ) {
        Surface(modifier = Modifier.fillMaxSize())
        {


            when (movieUiState) {
                is MoviesUIState.Error -> {
                    loading = false
                    ErrorComponent(movieUiState.msg, onDismiss = {
                        forceAppClose()
                    }, onRetry = {
                        viewModel.getAllMovies()
                    }
                    )
                }

                MoviesUIState.Loading -> {
                    ShimmerMovieList()
                }

                is MoviesUIState.Success -> {

                    loading = false
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Search(onQueryChanged = {
                                viewModel.filterMovies(it)
                            }, nightMode = (nightMode == MODE_DARK),
                                activity)

                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    viewModel.saveMode(
                                        context = context,
                                        if (nightMode == MODE_DARK) 0 else 1
                                    )
                                },
                            ) {
                                Icon(
                                    imageVector = if (nightMode == MODE_DARK) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                                    contentDescription = "Change between dark an light mode"
                                )
                            }
                        }

                        MovieList(movies = movieUiState.movies.sortedBy { it.movie.releaseState.formatDateComparable() }
                            .reversed(), onMovieSelected = {
                            viewModel.setSelectedMovie(it)
                        }, onShowDetailsPressed = {
                            viewModel.toogleDetails()
                        })
                        if (showDetails.value && selectedMovie.value != null) {
                            MovieDetails(
                                movie = selectedMovie.value!!, onDismiss = {
                                    viewModel.toogleDetails()
                                },
                                onNavigateToDetailsPressed = {
                                    viewModel.toogleDetails()
                                    navigationController.navigate(
                                        Routes.MovieFullDetails.createRoute(
                                            selectedMovie.value?.movie?.id.orEmpty()
                                        )
                                    )
                                },
                                activity
                            )

                        }
                    }
                }
            }
        }

    }
}








