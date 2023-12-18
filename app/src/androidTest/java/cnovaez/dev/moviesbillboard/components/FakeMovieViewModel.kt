package cnovaez.dev.moviesbillboard.components

import androidx.lifecycle.MutableLiveData

/**
 ** Created by Carlos A. Novaez Guerrero on 18/12/2023 17:12
 ** cnovaez.dev@outlook.com
 **/
class FakeMovieViewModel : MovieViewModelContract {

    val details = MutableLiveData(false)
    override fun showDetails(){
        details.postValue(true)
    }

}