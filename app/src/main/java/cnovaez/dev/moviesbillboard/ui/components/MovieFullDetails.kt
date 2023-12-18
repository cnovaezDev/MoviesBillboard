package cnovaez.dev.moviesbillboard.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cnovaez.dev.moviesbillboard.R
import cnovaez.dev.moviesbillboard.domain.models.Routes
import cnovaez.dev.moviesbillboard.ui.viewmodels.MainActivityViewModel
import cnovaez.dev.moviesbillboard.utils.ext.MODE_DARK
import cnovaez.dev.moviesbillboard.utils.ext.toTimeFormat

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 18:07
 ** cnovaez.dev@outlook.com
 **/

/**
 * Component used to show the full details of a movie, including all the fields
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieFullDetails(
    mainActivityViewModel: MainActivityViewModel,
    navHost: NavHostController
) {
    val movie by mainActivityViewModel.selectedMovie.observeAsState(initial = null)
    val nightMode by mainActivityViewModel.loadMode(LocalContext.current)
        .collectAsState(initial = 0)

    if (movie != null) {
        MaterialTheme(
            colorScheme = if (nightMode == MODE_DARK) darkColorScheme() else lightColorScheme(),
        ) {
            Surface {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
                ) {
                    Column {
                        IconButton(
                            onClick = { navHost.navigate(Routes.MoviesList.route) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close Details"
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .verticalScroll(
                                    rememberScrollState()
                                ),
                            verticalArrangement = Arrangement.Center
                        ) {

                            MovieImage(url = movie!!.movie.image, 300)

                            CustomMovieInfoRow(
                                imageVector = Icons.Filled.Movie,
                                text = movie!!.movie.fullTitle,
                                TextStyle(fontWeight = FontWeight.Bold),
                                Modifier.align(Alignment.CenterHorizontally),
                            )
                            CustomMovieInfoRow(
                                imageVector = Icons.Filled.DateRange,
                                text = movie!!.movie.releaseState,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )


                            CustomMovieInfoRow(
                                imageVector = Icons.Filled.Scoreboard,
                                text = "IMDb: ${movie!!.movie.imDbRating}, Metacritic: ${movie!!.movie.metacriticRating}",
                                modifier = Modifier.align(Alignment.Start)
                            )

                            CustomMovieInfoRow(
                                imageVector = Icons.Rounded.Info,
                                text = "Rated: ${movie!!.movie.contentRating}",
                                modifier = Modifier.align(Alignment.Start)
                            )
                            CustomMovieInfoRow(
                                imageVector = Icons.Filled.Timer,
                                text = movie!!.movie.runtimeMins.toTimeFormat(),
                                modifier = Modifier.align(Alignment.Start)
                            )
                            CustomMovieInfoRow(
                                imageVector = Icons.Filled.TextSnippet,
                                text = movie!!.movie.plot,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            if (movie!!.stars.isNotEmpty()) {
                                CustomList(
                                    stringResource(R.string.actors),
                                    movie!!.stars.map { it.name })
                            }
                            if (movie!!.directors.isNotEmpty()) {
                                CustomList(
                                    stringResource(R.string.directors),
                                    movie!!.directors.map { it.name })
                            }
                            if (movie!!.genres.isNotEmpty()) {
                                CustomList(
                                    stringResource(R.string.genres),
                                    movie!!.genres.map { it.value })
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun CustomMovieInfoRow(
    imageVector: ImageVector,
    text: String,
    textStyle: TextStyle? = null,
    modifier: Modifier,
) {
    Spacer(modifier = Modifier.size(4.dp))
    Row(modifier) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        if (textStyle != null) {
            Text(
                text = text,
                style = textStyle,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 4.dp),
                fontSize = 14.sp
            )
        } else {
            Text(
                text = text,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 4.dp),
                fontSize = 14.sp
            )
        }
    }

}
