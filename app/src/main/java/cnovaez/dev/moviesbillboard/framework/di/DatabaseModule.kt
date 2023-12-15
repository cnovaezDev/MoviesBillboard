package cnovaez.dev.moviesbillboard.framework.di

import dagger.Module
import dagger.hilt.InstallIn
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
    fun provideRoomDatabase() {

    }
}