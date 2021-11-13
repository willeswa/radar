package com.wilies.radar.database.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "current_weathers")
data class CurrentWeatherEntity(

        @PrimaryKey
        val dt: Long,
        val icon: String,
        val temp: Double,
        val windSpeed: Double,
        val uvi: Double,
        val humidity: Double,
        val feelsLike: Double
)


@Entity(tableName = "hourly_weathers")
data class HourlyWeatherEntity(

        @PrimaryKey
        val dt: Long,
        val icon: String,
        val temp: Double,
)


@Entity(tableName = "daily_weather")
data class DailyWeatherEntity(

        @PrimaryKey
        val dt: Long,
        val icon: String,
        val maxTemp: Double,
        val minTemp: Double,
        val daysTemp: Double,
        val uvi: Double,
        val windSpeed: Double,
        val description: String
)