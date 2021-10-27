package com.wilies.radar

import com.wilies.radar.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

interface WeatherApiService{
    @GET("data/2.5/onecall")
    fun getWeatherPredictions(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid:String ):
            Call<String>

}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}