package cnovaez.dev.moviesbillboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 17:06
 ** cnovaez.dev@outlook.com
 **/

@Composable
fun ShimmerMovieList() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ShimmerRow()
            ShimmerRow()
            ShimmerRow()
            ShimmerRow()
            ShimmerRow()
            ShimmerRow()
            ShimmerRow()
        }
    }
}

@Composable
private fun ShimmerItem(modifier: Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column {
            ShimmerInfo()
            ShimmerImage()
        }
    }
}

@Composable
private fun ShimmerRow() {
    Row {
        ShimmerItem(Modifier.weight(1f))
        ShimmerItem(Modifier.weight(1f))
    }
}


@Composable
private fun ShimmerImage() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clip(RoundedCornerShape(8.dp)) // Clip las esquinas del Box
            .background(ShimmerBrush())
    )
}
@Preview
@Composable
private fun ShimmerInfo() {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Movie,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "",
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier.width(150.dp)
                    .clip(RoundedCornerShape(8.dp)).background(ShimmerBrush()))

        }
        Row {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "",
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier.width(100.dp)
                    .clip(RoundedCornerShape(8.dp)).background(ShimmerBrush()))
        }
        Row {
            Icon(
                imageVector = Icons.Filled.Scoreboard,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "",
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier.width(40.dp)
                    .clip(RoundedCornerShape(8.dp)).background(ShimmerBrush()))
        }
    }
}
