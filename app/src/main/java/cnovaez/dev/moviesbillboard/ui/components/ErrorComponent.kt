package cnovaez.dev.moviesbillboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cnovaez.dev.moviesbillboard.R

/**
 ** Created by Carlos A. Novaez Guerrero on 18/12/2023 0:34
 ** cnovaez.dev@outlook.com
 **/
@Composable
fun ErrorComponent(msg: String, onDismiss: () -> Unit, onRetry: () -> Unit) {
    Dialog(onDismissRequest = { }) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error image"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "An error has ocurred: $msg .\n Please wait a few moments and try again",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = { onRetry() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C581))
                    ) {
                        Text(text = "Retry")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE96F6F))
                    ) {
                        Text(text = "Exit")
                    }

                }
            }
        }
    }
}

