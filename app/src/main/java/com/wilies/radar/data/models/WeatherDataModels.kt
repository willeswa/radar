package com.wilies.radar.data.models

import com.squareup.moshi.Json

data class WeatherResponse(
    val lat: Double,
    val lon: Double,
    @Json(name = "current") val current: Weather,
    @Json(name = "hourly") val hourly: List<Weather>,
    @Json(name = "daily") val daily: List<DailyWeather>

)


data class Weather(
    val dt: Double,
    val temp: Double,
    val uvi: Double,
    val wind_speed: Double,
    val humidity: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "weather") val weather: List<WeatherDescription>

)

data class DailyWeather(
        val dt: Double,
        val temp: Temp,
        val uvi: Double,
        val wind_speed: Double,
        val humidity: Double,
        @Json(name = "weather") val weather: List<WeatherDescription>
)

data class Temp(val day: Double, val max: Double, val min: Double)

data class WeatherDescription(val id: Int, val description: String, val icon: String)
