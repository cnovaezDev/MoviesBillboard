package cnovaez.dev.moviesbillboard.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.ui.MoviesUIState
import cnovaez.dev.moviesbillboard.use_case.GetAllFilteredMoviesUseCase
import cnovaez.dev.moviesbillboard.use_case.GetAllMoviesUseCase
import cnovaez.dev.moviesbillboard.utils.ext.loadMode
import cnovaez.dev.moviesbillboard.utils.ext.saveMode
import cnovaez.dev.moviesbillboard.utils.ext.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:41
 ** cnovaez.dev@outlook.com
 **/
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getAllFilteredMoviesUseCase: GetAllFilteredMoviesUseCase,
) : ViewModel() {

    val movies = mutableStateOf<MoviesUIState>(MoviesUIState.Loading)

    private val _showDetails: MutableLiveData<Boolean> = MutableLiveData(false)
    val showDetails: LiveData<Boolean> get() = _showDetails

    private val _selectedMovie: MutableLiveData<MovieWithDetails> = MutableLiveData(null)
    val selectedMovie: LiveData<MovieWithDetails> get() = _selectedMovie

    private var _nightMode = MutableLiveData<Boolean>()
    val nightMode: LiveData<Boolean>
        get() = _nightMode


    fun getAllMovies() {
        movies.value = MoviesUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                Log.i("Calling get all movies", "started")
                val response = getAllMoviesUseCase()
                Log.i("Movies:", "${response?.toJson()}")
                response?.let {
                    withContext(Dispatchers.Main) {
                        if (it.movies.isNotEmpty()) {
                            movies.value = MoviesUIState.Success(it.movies)
                        } else {
                            movies.value = MoviesUIState.Error(it.errorMessage)
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                withContext(Dispatchers.Main) {
                    movies.value = MoviesUIState.Error(ex.message ?: "Error unknown")
                }
            }
        }
    }

    fun toogleDetails() {
        _showDetails.value = _showDetails.value?.not()
    }

    fun setSelectedMovie(movie: MovieWithDetails) {
        _selectedMovie.value = movie
    }


    fun saveMode(context: Context, mode: Int) {
        viewModelScope.launch {
            context.saveMode(mode)
        }
    }

    fun loadMode(context: Context): Flow<Int> {
        return context.loadMode()
    }

    fun filterMovies(it: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getAllFilteredMoviesUseCase(it)
                result?.let {
                    movies.value = MoviesUIState.Success(it.movies)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                movies.value = MoviesUIState.Error(ex.message ?: "Error unknown")
            }
        }
    }
}