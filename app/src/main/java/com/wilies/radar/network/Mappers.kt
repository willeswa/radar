package com.wilies.radar.network

import com.wilies.radar.database.models.DailyWeatherEntity
import com.wilies.radar.database.models.TempEntity
import com.wilies.radar.database.models.WeatherDescriptionEntity
import com.wilies.radar.database.models.WeatherEntity

fun DTOWeather.asDatabaseModel(): WeatherEntity{
    return WeatherEntity(
        dt = dt,
        temp = temp,
        uvi = uvi,
        wind_speed = windSpeed,
        humidity = humidity,
        feelsLike = feelsLike,
        weatherDesc = weatherDescription.map {
            WeatherDescriptionEntity(
                description = it.description,
                icon = it.icon,
                associatedWeatherId = dt
            )
        },
        associatedWeatherId = dt
    )
}

fun List<DTODailyWeather>.asDailyDataModel(): Array<DailyWeatherEntity>{
    return map{it ->
        DailyWeatherEntity(
        dt = it.dt,
        uvi = it.uvi,
        temp = TempEntity(
            day = it.temp.day,
            min = it.temp.min,
            max = it.temp.max,
            associatedWeatherId = it.dt
        ),
        wind_speed = it.windSpeed,
        weatherDesc = it.weatherDescription.map {desc ->
            WeatherDescriptionEntity(
                description = desc.description,
                icon = desc.icon,
                associatedWeatherId = it.dt
            )
        }
    )}.toTypedArray()
}

fun List<DTOWeather>.asHourlyDataModel(): Array<WeatherEntity>{
    return map{
        WeatherEntity(
            dt = it.dt,
            temp = it.temp,
            uvi = it.uvi,
            wind_speed = it.windSpeed,
            humidity = it.humidity,
            feelsLike = it.feelsLike,
            weatherDesc = it.weatherDescription.map {desc ->
                WeatherDescriptionEntity(
                    description = desc.description,
                    icon = desc.icon,
                    associatedWeatherId = it.dt
                )
            },
            associatedWeatherId = it.dt
        )}.toTypedArray()
}