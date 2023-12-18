package cnovaez.dev.moviesbillboard.ui.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import cnovaez.dev.moviesbillboard.R
import cnovaez.dev.moviesbillboard.ui.MainActivity

/**
 ** Created by Carlos A. Novaez Guerrero on 18/12/2023 0:53
 ** cnovaez.dev@outlook.com
 **/

/**
 * Component used to request a single permission at the time to the user
 */
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
            onDismiss = {
                deniedPermissionDialogShown = false
            }
        )
    }


    LaunchedEffect(true) {
        launcher.launch(permission)
    }
}

/**
 * Component used to request multiple permissions at the time to the user
 */
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
            },
            required = true
        )
    }

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
    onDismiss: () -> Unit,
    required: Boolean = false
) {
    Dialog(onDismissRequest = { if (!required) onDismiss() }) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Permission type icon",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = if (!required) stringResource(R.string.permission_is_not_mandatory) else stringResource(
                            R.string.permission_is_mandatory
                        ),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )


                }
                Text(
                    text = "Permission $permission was denied",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp)
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
