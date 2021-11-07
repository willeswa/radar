package com.wilies.radar.database

import com.wilies.radar.database.models.DailyWeatherEntity
import com.wilies.radar.database.models.WeatherDescriptionEntity
import com.wilies.radar.database.models.WeatherEntity
import com.wilies.radar.domain.*


fun List<CurrentWeatherWithDescription>.asDomainModel(): List<WeatherWithDescription>{
    return map { it ->
        WeatherWithDescription(
            current = Weather(
                dt = it.currentWeather.dt,
                temp = it.currentWeather.temp,
                uvi = it.currentWeather.uvi,
                wind_speed = it.currentWeather.wind_speed,
                humidity = it.currentWeather.humidity,
                feelsLike = it.currentWeather.feelsLike,
                associatedWeather = it.currentWeather.dt,
                weatherDes = it.currentWeather.weatherDesc?.map{desc ->
                    WeatherDescription(
                        description = desc.description,
                        icon = desc.icon,
                        associatedWeatherId = it.currentWeather.dt

                    )
                }
            ),
            hourly = it.hourlyWeather.map { it ->
                Weather(
                    dt = it.dt,
                    temp = it.temp,
                    uvi = it.uvi,
                    wind_speed = it.wind_speed,
                    humidity = it.humidity,
                    feelsLike = it.feelsLike,
                    associatedWeather = it.dt,
                    weatherDes = it.weatherDesc?.map{desc ->
                        WeatherDescription(
                            description = desc.description,
                            icon = desc.icon,
                            associatedWeatherId = it.dt
                        )
                    }
                )
            },
            daily = it.dailyWeather.map { it ->
                DailyWeather(
                    dt = it.dt,
                    temp = Temp(
                        day = it.temp?.day,
                        min = it.temp?.min,
                        max = it.temp?.max
                    ),
                    uvi = it.uvi,
                    wind_speed = it.wind_speed,
                    weatherDes = it.weatherDesc?.map {desc ->
                        WeatherDescription(
                            description = desc.description,
                            icon = desc.icon,
                            associatedWeatherId = it.dt
                        )
                    }
                )
            }
        )
    }
}


fun List<WeatherEntity>.toHourlyDomain(): List<Weather>{
    return map {
        Weather(
            dt = it.dt,
            temp = it.temp,
            uvi = it.uvi,
            wind_speed = it.wind_speed,
            humidity = it.humidity,
            feelsLike = it.feelsLike,
            weatherDes = null,
            associatedWeather = it.associatedWeatherId
        )
    }
}


fun List<DailyWeatherEntity>.toDailyDomain(): List<DailyWeather>{
    return map{
        DailyWeather(
            dt = it.dt,
            temp = null,
            uvi = it.uvi,
            wind_speed = it.wind_speed,
            weatherDes = null,
        )
    }
}

fun List<WeatherDescriptionEntity>.toDescriptionDomain(): List<WeatherDescription>{
    return map{
        WeatherDescription(
            description = it.description,
            icon = it.icon,
            associatedWeatherId = it.associatedWeatherId
        )
    }
}