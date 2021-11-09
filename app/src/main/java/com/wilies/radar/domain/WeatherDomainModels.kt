package com.wilies.radar.domain



data class CurrentWeatherDomain(
        val dt: Long,
        val icon: String,
        val temp: Double,
        val windSpeed: Double,
        val uvi: Double,
        val humidity: Double,
        val feelsLike: Double
)


data class HourlyWeatherDomain(
        val dt: Long,
        val icon: String,
        val temp: Double,
)


data class DailyWeatherDomain(
        val dt: Long,
        val icon: String,
        val maxTemp: Double,
        val minTemp: Double,
        val daysTemp: Double,
        val uvi: Double,
        val windSpeed: Double
)