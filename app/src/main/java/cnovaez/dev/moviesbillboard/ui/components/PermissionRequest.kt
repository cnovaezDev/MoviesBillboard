package cnovaez.dev.moviesbillboard.ui.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import cnovaez.dev.moviesbillboard.ui.MainActivity

/**
 ** Created by Carlos A. Novaez Guerrero on 18/12/2023 0:53
 ** cnovaez.dev@outlook.com
 **/

@Composable
fun PermissionScreen(permission: String, activity: MainActivity) {
    var deniedPermissionDialogShown by remember { mutableStateOf(false) }
    var permanently by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->

        if (isGranted) {
            Log.i("Permission", "Granted")
        } else {
            permanently = !shouldShowRequestPermissionRationale(activity, permission)
            deniedPermissionDialogShown = true
        }
    }
    if (deniedPermissionDialogShown) {
        DeniedPermissionScreen(
            permission = permission,
            onRetryClicked = {
                launcher.launch(permission)
                deniedPermissionDialogShown = false

            },
            permanently = permanently,
            activity = activity,
            {
                deniedPermissionDialogShown = false
            }
        )
    }


    // Para solicitar el permiso, llama al launcher
    LaunchedEffect(true) {
        launcher.launch(permission)
    }
}

@Composable
fun PermissionScreen(permissions: List<String>, activity: MainActivity) {
    var deniedPermissionDialogShown by remember { mutableStateOf(false) }
    var permanently by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsResult: Map<String, Boolean> ->
        permissionsResult.forEach { (permission, isGranted) ->
            if (isGranted) {
                Log.i("Permission", "$permission Granted")
            } else {
                permanently = !shouldShowRequestPermissionRationale(activity, permission)
                deniedPermissionDialogShown = true
            }
        }
    }
    if (deniedPermissionDialogShown) {
        DeniedPermissionScreen(
            permission = "Location",
            onRetryClicked = {
                launcher.launch(permissions.toTypedArray())
                deniedPermissionDialogShown = false

            },
            permanently = permanently,
            activity = activity,
            {
                deniedPermissionDialogShown = false
            }
        )
    }

    // Para solicitar los permisos, llama al launcher con la lista de permisos
    LaunchedEffect(true) {
        launcher.launch(permissions.toTypedArray())
    }
}

@Composable
fun DeniedPermissionScreen(
    permission: String,
    onRetryClicked: () -> Unit,
    permanently: Boolean,
    activity: MainActivity,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Permission $permission was denied",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                if (!permanently) {
                    Button(onClick = { onRetryClicked() }) {
                        Text(text = "Try Again")
                    }
                } else {
                    Button(onClick = {
                        goToSettings(activity)
                        onDismiss()
                    }) {
                        Text(text = "Open Settings")
                    }
                }
            }
        }
    }
}

private fun goToSettings(activity: MainActivity) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", activity.packageName, null)
    intent.data = uri
    activity.startActivity(intent)
}
