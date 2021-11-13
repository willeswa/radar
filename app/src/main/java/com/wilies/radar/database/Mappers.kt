package com.wilies.radar.database

import com.wilies.radar.database.models.CurrentWeatherEntity
import com.wilies.radar.database.models.DailyWeatherEntity
import com.wilies.radar.database.models.HourlyWeatherEntity
import com.wilies.radar.domain.CurrentWeatherDomain
import com.wilies.radar.domain.DailyWeatherDomain
import com.wilies.radar.domain.HourlyWeatherDomain


fun CurrentWeatherEntity.asCurrentWeatherDomain(): CurrentWeatherDomain {
    return let{
         CurrentWeatherDomain(
             dt = it.dt,
             icon = it.icon,
             temp = it.temp,
             windSpeed = it.windSpeed,
             uvi = it.uvi,
             humidity = it.humidity,
             feelsLike = it.feelsLike
         )

    }
}

fun List<HourlyWeatherEntity>.asHourlyWeatherDomain(): List<HourlyWeatherDomain>{
    return map {
        HourlyWeatherDomain(
            dt = it.dt,
            icon = it.icon,
            temp = it.temp,
        )
    }
}


fun List<DailyWeatherEntity>.asDailyWeatherDomain(): List<DailyWeatherDomain>{
    return map {
        DailyWeatherDomain(
            dt = it.dt,
            icon = it.icon,
            maxTemp = it.maxTemp,
            minTemp = it.minTemp,
            daysTemp = it.daysTemp,
            uvi = it.uvi,
            windSpeed = it.windSpeed,
            description = it.description
        )
    }
}