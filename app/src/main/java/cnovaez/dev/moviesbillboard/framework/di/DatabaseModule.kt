package cnovaez.dev.moviesbillboard.framework.di

import android.content.Context
import androidx.room.Room
import cnovaez.dev.moviesbillboard.domain.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:05
 ** cnovaez.dev@outlook.com
 **/
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "MoviesDatabase")
            .build()


    @Singleton
    @Provides
    fun provideMovieDao(db: MoviesDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideGenreDao(db: MoviesDatabase) = db.genreDao()

    @Singleton
    @Provides
    fun provideDirectorDao(db: MoviesDatabase) = db.directorDao()

    @Singleton
    @Provides
    fun provideStarDao(db: MoviesDatabase) = db.starDao()

    @Singleton
    @Provides
    fun provideMovieGenreCrossRefDao(db: MoviesDatabase) = db.movieGenreCrossRefDao()

    @Singleton
    @Provides
    fun provideMovieDirectorCrossRefDao(db: MoviesDatabase) = db.movieDirectorCrossRefDao()

    @Singleton
    @Provides
    fun provideMovieStarCrossRefDao(db: MoviesDatabase) = db.movieStarCrossRefDao()




}