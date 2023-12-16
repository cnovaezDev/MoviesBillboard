package cnovaez.dev.moviesbillboard.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.Gson
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 21:49
 ** cnovaez.dev@outlook.com
 **/

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

//fun compareDates(){
//    val fecha1 = "09 jul 2010"
//    val fecha2 = "10 apr 2020"
//
//    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
//
//    val date1 = LocalDate.parse(fecha1, formatter)
//    val date2 = LocalDate.parse(fecha2, formatter)
//
//    // Ahora puedes comparar las fechas
//    if (date1.isBefore(date2)) {
//        println("$fecha1 es anterior a $fecha2")
//    } else if (date1.isAfter(date2)) {
//        println("$fecha1 es posterior a $fecha2")
//    } else {
//        println("$fecha1 y $fecha2 son iguales")
//    }
//}