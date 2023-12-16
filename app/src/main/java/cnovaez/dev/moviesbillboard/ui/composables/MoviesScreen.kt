package cnovaez.dev.moviesbillboard.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cnovaez.dev.moviesbillboard.R
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.ui.viewmodels.MainActivityViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 22:31
 ** cnovaez.dev@outlook.com
 **/

@Composable
fun MoviesScreen(viewModel: MainActivityViewModel) {
    val showDetails = viewModel.showDetails.observeAsState(initial = false)
    val selectedMovie = viewModel.selectedMovie.observeAsState(initial = null)
    MovieList(viewModel = viewModel)
    if (showDetails.value && selectedMovie.value != null) {
        MovieDetails(movie = selectedMovie.value!!) {
            viewModel.toogleDetails()
        }
    }

}

@Composable
fun MovieList(viewModel: MainActivityViewModel) {
    val movies = viewModel.movies.sortedBy { it.movie.releaseState }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    viewModel.setSelectedMovie(movie)
                    viewModel.toogleDetails()
                }
            }
        }
    }
}

@Composable
fun MovieDetails(movie: MovieWithDetails, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column {
            Text(text = movie.movie.title)
            Text(text = movie.movie.releaseState)
            Text(text = movie.movie.fullTitle)
            Text(text = movie.movie.plot)
            Text(text = movie.movie.runtimeMins)
            Text(text = movie.movie.year)
            Text(text = movie.movie.contentRating)
            Text(text = movie.movie.imDbRating)
        }
    }
}

@Composable
fun MovieItem(movie: MovieWithDetails, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            MovieImage(url = movie.movie.image)
            MovieInfo(movie = movie)
        }

    }
}

@Composable
fun MovieImage(url: String) {
    val context = LocalContext.current
    val imageRequest = remember { ImageRequest.Builder(context).data(url).crossfade(true).build() }
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = imageRequest,
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = stringResource(R.string.description),
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
                .align(Alignment.Center)
                .clip(AbsoluteRoundedCornerShape(8.dp)) //no se esta aplicando
        )

    }
}

@Composable
fun MovieInfo(movie: MovieWithDetails) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = movie.movie.title)
            Text(text = movie.movie.releaseState)
//            Text(text = movie.movie.fullTitle)
//            Text(text = movie.movie.plot)
//            Text(text = movie.movie.runtimeMins)
//            Text(text = movie.movie.year)
//            Text(text = movie.movie.contentRating)
//            Text(text = movie.movie.imDbRating)
        }
    }
}


