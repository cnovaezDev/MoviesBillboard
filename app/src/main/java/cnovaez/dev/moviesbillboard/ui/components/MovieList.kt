package cnovaez.dev.moviesbillboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.utils.constants.TestsConstants

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 14:45
 ** cnovaez.dev@outlook.com
 **/

/**
 * Component used to show the list of movies to the user, this component is used in the main screen of the app
 * is a vertical grid with 2 columns
 **/
@Composable
fun MovieList(
    movies: List<MovieWithDetails>,
    onMovieSelected: (MovieWithDetails) -> Unit,
    onShowDetailsPressed: (Boolean) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.testTag(TestsConstants.MOVIE_LIST),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.SpaceBetween,
        columns = GridCells.Fixed(2),
        content = {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    onMovieSelected(movie)
                    onShowDetailsPressed(true)
                }
            }
        })
}