package cnovaez.dev.moviesbillboard.ui.components

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cnovaez.dev.moviesbillboard.R
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.ui.MainActivity
import cnovaez.dev.moviesbillboard.utils.constants.TestsConstants
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 18:07
 ** cnovaez.dev@outlook.com
 **/

/**
 * Component used to show the details of a movie
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieDetails(
    movie: MovieWithDetails,
    onDismiss: () -> Unit,
    onNavigateToDetailsPressed: () -> Unit,
    activity: MainActivity?
) {
    if(activity!=null){
        PermissionScreen(
            permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            activity
        )
    }
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .testTag(TestsConstants.MOVIE_DETAILS)
        ) {
            Column {
                IconButton(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .align(Alignment.End)

                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Dialog")
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

                    MovieImage(url = movie.movie.image)

                    CustomMovieInfoRow(
                        imageVector = Icons.Filled.Movie,
                        text = movie.movie.fullTitle,
                        TextStyle(fontWeight = FontWeight.Bold),
                        Modifier.align(Alignment.CenterHorizontally),
                    )
                    CustomMovieInfoRow(
                        imageVector = Icons.Filled.DateRange,
                        text = movie.movie.releaseState,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    CustomMovieInfoRow(
                        imageVector = Icons.Filled.TextSnippet,
                        text = movie.movie.plot,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    if (movie.genres.isNotEmpty()) {
                        CustomList(stringResource(R.string.genres), movie.genres.map { it.value })
                    }

                    Button(
                        onClick = { onNavigateToDetailsPressed() },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 16.dp)
                    ) {
                        Text(text = "Full Details")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomList(header: String, stars: List<String>) {
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        Text(
            text = header,
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = stars.map { "${it}" }.toString().replace("[", "")
                .replace("]", ""),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
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
