package cnovaez.dev.moviesbillboard.ui.components

import android.Manifest
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cnovaez.dev.moviesbillboard.R
import cnovaez.dev.moviesbillboard.ui.MainActivity
import java.util.Timer
import java.util.TimerTask

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 22:42
 ** cnovaez.dev@outlook.com
 **/

var timer: Timer = Timer()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(onQueryChanged: (String) -> Unit, nightMode: Boolean, activity: MainActivity) {

    var showFilter by rememberSaveable { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }



    Row {
        if (!showFilter) {
            IconButton(onClick = { showFilter = true }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                )
            }
        } else {
            PermissionScreen(Manifest.permission.CAMERA, activity)

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 4.dp, end = 4.dp)
            ) {
                Badge(
                    Modifier
                        .fillMaxWidth(),
                    containerColor = if (nightMode) Color.DarkGray else Color.LightGray

                ) {
                    IconButton(onClick = { showFilter = false }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = if (nightMode) Color.White else
                                Color.Black,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        )
                    }

                    //TextField sin el borde exterior
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it

                            timer.cancel()
                            timer = Timer()

                            timer.schedule(object : TimerTask() {
                                override fun run() {
                                    onQueryChanged(it)
                                }
                            }, 500)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.search),
                                fontSize = 10.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.align(Alignment.Top)
                            )
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                            .padding(start = 8.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        textStyle = TextStyle(fontSize = 10.sp)

                    )
                    IconButton(onClick = {
                        if (searchQuery.isNotEmpty()) {
                            searchQuery = ""
                            onQueryChanged("")
                        } else {
                            showFilter = false
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = if (nightMode) Color.White else
                                Color.Black,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }

                }
            }
        }
    }
}

