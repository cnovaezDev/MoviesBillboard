package cnovaez.dev.moviesbillboard.data.network

import cnovaez.dev.moviesbillboard.domain.network_response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.Flow

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:58
 ** cnovaez.dev@outlook.com
 **/
interface MoviesApi {

    @GET("/movies.json?key=cb03b960")
    suspend fun getMovies(): MoviesResponse
}