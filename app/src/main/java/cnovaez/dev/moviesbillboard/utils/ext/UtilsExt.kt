package cnovaez.dev.moviesbillboard.utils.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ThreadContextElement
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.coroutines.CoroutineContext
import kotlin.system.exitProcess

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 21:49
 ** cnovaez.dev@outlook.com
 **/

const val MODE_DARK = 1
const val MODE_LIGTH = 1
fun Context.isDeviceIsConnectedToTheInternet(): Boolean {
    return (isNetworkConnected(this) && isInternetAvailable(this))
}


fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)

        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    } else {
        // For devices running versions prior to Android M
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)

        return capabilities != null &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        // For devices running versions prior to Android M
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

fun Any.toJson() =
    Gson().toJson(this)

fun String.toTimeFormat(): String {
    return try {
        val intValue = this.toInt()
        val hours = intValue / 60
        val minutes = intValue % 60
        "$hours h $minutes min"
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}


fun String.formatDateComparable(): Long {
    return try {
        val formatoOriginal = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        val fechaDate = formatoOriginal.parse(this)
        val formatoNuevo = SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
        return formatoNuevo.format(fechaDate).toLong()
    } catch (ex: Exception) {
        ex.printStackTrace()
        0L
    }
}

fun forceAppClose() {
    exitProcess(0)
}
