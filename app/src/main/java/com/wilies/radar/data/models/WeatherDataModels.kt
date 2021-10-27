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
    @Json(name = "weather") val weather: List<WeatherDescription>
)

data class WeatherDescription(val id: Int, val main: String)
