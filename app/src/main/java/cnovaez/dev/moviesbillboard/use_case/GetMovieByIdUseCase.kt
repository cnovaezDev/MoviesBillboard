package cnovaez.dev.moviesbillboard.use_case

import cnovaez.dev.moviesbillboard.data.repository.MoviesRepository
import javax.inject.Inject

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 21:45
 ** cnovaez.dev@outlook.com
 **/
class GetMovieByIdUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: String) = moviesRepository.getMovieById(movieId)
}