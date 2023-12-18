package cnovaez.dev.moviesbillboard.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 17:08
 ** cnovaez.dev@outlook.com
 **/

@Composable
fun MovieItem(movie: MovieWithDetails, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                MovieInfo(movie = movie)
                MovieImage(url = movie.movie?.image.orEmpty())
            }
        }

    }
}

@Composable
private fun MovieInfo(movie: MovieWithDetails) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        CustomHeaderMovieInfoRow(Icons.Filled.Movie, movie.movie.title)
        CustomHeaderMovieInfoRow(Icons.Filled.DateRange, movie.movie.releaseState)
        CustomHeaderMovieInfoRow(Icons.Filled.Scoreboard, "IMDb: ${movie.movie.imDbRating}")

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CustomHeaderMovieInfoRow(
    imageVector: ImageVector,
    text: String,
) {
    Row {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            maxLines = 1,
            modifier = Modifier
                .basicMarquee()
                .padding(start = 4.dp),
            fontSize = 14.sp
        )
    }

}