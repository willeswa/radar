package com.wilies.radar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wilies.radar.network.NetworkResponseContainer
import com.wilies.radar.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

interface WeatherApiService{
    @GET("data/2.5/onecall")
    suspend fun getWeatherPredictions(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid:String ): NetworkResponseContainer
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}