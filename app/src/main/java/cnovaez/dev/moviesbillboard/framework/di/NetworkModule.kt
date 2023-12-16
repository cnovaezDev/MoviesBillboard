package cnovaez.dev.moviesbillboard.framework.di

import cnovaez.dev.moviesbillboard.data.network.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 ** Created by Carlos A. Novaez Guerrero on 15/12/2023 18:55
 ** cnovaez.dev@outlook.com
 **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://my.api.mockaroo.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

}