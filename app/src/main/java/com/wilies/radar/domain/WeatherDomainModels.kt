package com.wilies.radar.domain



data class WeatherWithDescription(
        val current: Weather?,
        val hourly: List<Weather>?,
        val daily: List<DailyWeather>?
)

data class Weather(
        val dt: Double,
        val temp: Double,
        val uvi: Double,
        val wind_speed: Double,
        val humidity: Double,
        val feelsLike: Double,
        val weatherDes: List<WeatherDescription>?,
        val associatedWeather: Double
)

data class Temp(val day: Double?, val max: Double?, val min: Double?)

data class DailyWeather(
        val dt: Double,
        val temp: Temp?,
        val uvi: Double,
        val wind_speed: Double,
        val weatherDes: List<WeatherDescription>?
)

data class WeatherDescription(val description: String, val icon: String, val associatedWeatherId: Double)
