package com.wilies.radar.data.models

import com.squareup.moshi.Json

data class WeatherResponse(
    val lat: Double,
    val lon: Double,
    @Json(name = "current") val current: Weather,
    @Json(name = "hourly") val hourly: List<Weather>

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

data class WeatherDescription(val id: Int, val description: String, val icon: String)
