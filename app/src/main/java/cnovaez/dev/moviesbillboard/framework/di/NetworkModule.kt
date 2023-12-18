package cnovaez.dev.moviesbillboard.framework.di

import cnovaez.dev.moviesbillboard.data.network.MoviesApi
import cnovaez.dev.moviesbillboard.utils.constants.GeneralConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(GeneralConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

}