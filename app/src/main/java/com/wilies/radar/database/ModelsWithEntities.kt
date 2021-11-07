package com.wilies.radar.database

import androidx.room.Embedded
import androidx.room.Relation
import com.wilies.radar.database.models.DailyWeatherEntity
import com.wilies.radar.database.models.HourlyWeatherEntity
import com.wilies.radar.database.models.WeatherEntity

data class CurrentWeatherWithDescription(
        @Embedded val currentWeather: WeatherEntity,

        @Relation(
                parentColumn = "dt",
                entityColumn = "dt"
        )
        val hourlyWeather: List<WeatherEntity>,

        @Relation(
                parentColumn = "dt",
                entityColumn = "dt"
        )
        val dailyWeather: List<DailyWeatherEntity>
)