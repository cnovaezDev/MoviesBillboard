package cnovaez.dev.moviesbillboard

import android.app.Application
import coil.Coil
import dagger.hilt.android.HiltAndroidApp

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:39
 ** cnovaez.dev@outlook.com
 **/

@HiltAndroidApp
class MoviesBillboardApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Coil.imageLoader(this)
    }
}