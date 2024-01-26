package cnovaez.dev.moviesbillboard.framework.di

import android.app.Application
import android.content.Context
import cnovaez.dev.moviesbillboard.MoviesBillboardApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 ** Created by Carlos A. Novaez Guerrero on 09/01/2024 9:36
 ** cnovaez.dev@outlook.com
 **/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext appContext: Context): MoviesBillboardApp {
        return appContext as MoviesBillboardApp
    }

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context) = context
}