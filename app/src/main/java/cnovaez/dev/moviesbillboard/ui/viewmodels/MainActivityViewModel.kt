package cnovaez.dev.moviesbillboard.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.moviesbillboard.domain.database.entities.MovieWithDetails
import cnovaez.dev.moviesbillboard.use_case.GetAllMoviesUseCase
import cnovaez.dev.moviesbillboard.utils.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 19:41
 ** cnovaez.dev@outlook.com
 **/
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    // private val _movies = MutableStateFlow<MainUIState>(MainUIState.Loading)

    private val _movies = mutableStateListOf<MovieWithDetails>()
    val movies: List<MovieWithDetails> get() = _movies

    private val _showDetails: MutableLiveData<Boolean> = MutableLiveData(false)
    val showDetails: LiveData<Boolean> get() = _showDetails

    private val _selectedMovie: MutableLiveData<MovieWithDetails> = MutableLiveData(null)
    val selectedMovie: LiveData<MovieWithDetails> get() = _selectedMovie


    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("Calling get all movies", "started")
            val response = getAllMoviesUseCase()
            Log.i("Movies:", "${response.toJson()}")
            _movies.addAll(response)
        }
    }

    fun toogleDetails() {
        _showDetails.value = _showDetails.value?.not()
    }

    fun setSelectedMovie(movie: MovieWithDetails) {
        _selectedMovie.value = movie
    }


}