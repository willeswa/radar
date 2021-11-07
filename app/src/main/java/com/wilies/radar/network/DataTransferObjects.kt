package com.wilies.radar.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkResponseContainer(
        val current: DTOWeather,
        val hourly: List<DTOWeather>,
        val daily: List<DTODailyWeather>
)

@JsonClass(generateAdapter = true)
data class DTOWeather(
        val dt: Double,
        val temp: Double,
        @Json(name = "feels_like") val feelsLike: Double,
        @Json(name = "wind_speed") val windSpeed: Double,
        val humidity: Double,
        val uvi: Double,
        @Json(name = "weather") val weatherDescription: List<DTOWeatherDescription>
)

@JsonClass(generateAdapter = true)
data class DTOWeatherDescription(
        val description: String,
        val icon: String
)

@JsonClass(generateAdapter = true)
data class DTODailyWeather(
        val dt: Double,
        val uvi: Double,
        @Json(name = "wind_speed") val windSpeed: Double,
        val temp: DTOTemp,
        @Json(name = "weather") val weatherDescription: List<DTOWeatherDescription>
)


@JsonClass(generateAdapter = true)
data class DTOTemp(
        val day: Double,
        val min: Double,
        val max: Double
)









