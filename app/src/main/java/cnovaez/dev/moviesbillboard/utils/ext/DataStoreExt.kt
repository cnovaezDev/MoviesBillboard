package cnovaez.dev.moviesbillboard.utils.ext

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 19:31
 ** cnovaez.dev@outlook.com
 **/
val Context.dataStore by preferencesDataStore(name = "dark_mode")

val mode = intPreferencesKey("mode")

suspend fun Context.saveMode(counter: Int) {
    dataStore.edit { preferences ->
        preferences[mode] = counter
    }
}

fun Context.loadMode(): Flow<Int> = dataStore.data
    .map { preferences ->
        preferences[mode] ?: 0
    }