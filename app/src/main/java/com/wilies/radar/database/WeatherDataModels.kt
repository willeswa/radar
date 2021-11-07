package com.wilies.radar.database.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "hourly_weather")
data class HourlyWeatherEntity constructor(
        @PrimaryKey
        var dt: Double,
        var temp: Double,
        var associatedWeatherId: Double,
)


@Entity(tableName = "current_weather")
data class WeatherEntity constructor(
        @PrimaryKey
        var dt: Double,
        var temp: Double,
        var uvi: Double,
        var wind_speed: Double,
        var humidity: Double,
        var feelsLike: Double,
        var associatedWeatherId: Double,
        @Ignore var weatherDesc: List<WeatherDescriptionEntity>?

){
        constructor(

            ) : this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,null)
}

@Entity(tableName = "daily_weathers")
data class DailyWeatherEntity(
        @PrimaryKey
        var dt: Double,
        var uvi: Double,
        var wind_speed: Double,
        @Ignore var temp: TempEntity?,
        @Ignore var weatherDesc: List<WeatherDescriptionEntity>?
){
        constructor(): this(0.0, 0.0, 0.0, null, null)
}

@Entity(tableName = "weather_descriptions")
data class WeatherDescriptionEntity constructor(
        @PrimaryKey
        var description: String,
        var icon: String,
        var associatedWeatherId: Double,
     )


@Entity(tableName = "temp")
data class TempEntity constructor(
        @PrimaryKey
        var day: Double,
        var max: Double,
        var min: Double,
        var associatedWeatherId: Double,
        )




