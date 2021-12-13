package com.example.rickadnmortykotlin.data

import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val okHttpClient:OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor())
        .connectTimeout(30,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(30,TimeUnit.SECONDS)
        .build()
    private fun provideLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val provideRetrofitClient = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideCharacterApi():CharactersApi = provideRetrofitClient.create(
        CharactersApi::class.java
    )

    fun provideLocationApi():LocationApi = provideRetrofitClient.create(
        LocationApi::class.java
    )

    fun provideEpisodeApi():EpisodesApi = provideRetrofitClient.create(
        EpisodesApi::class.java
    )
}