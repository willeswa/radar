package com.wilies.radar.network

import com.wilies.radar.database.models.CurrentWeatherEntity
import com.wilies.radar.database.models.DailyWeatherEntity
import com.wilies.radar.database.models.HourlyWeatherEntity


fun NetworkResponseContainer.asCurrentWeatherDataModel(): CurrentWeatherEntity{
    return CurrentWeatherEntity(
        dt = this.current.dt,
        icon = this.current.weatherDescription[0].icon,
        temp =this.current.temp,
        windSpeed = this.current.windSpeed,
        uvi = this.current.uvi,
        humidity = this.current.humidity,
        feelsLike = this.current.feelsLike
    )
}


fun NetworkResponseContainer.asHourlyWeatherDataModel(): Array<HourlyWeatherEntity>{
    return hourly.map{
        HourlyWeatherEntity(
            dt = it.dt,
            icon = it.weatherDescription[0].icon,
            temp = it.temp
        )
    }.toTypedArray()
}


fun NetworkResponseContainer.asDailyWeatherDataModel(): Array<DailyWeatherEntity>{
    return daily.map{
        DailyWeatherEntity(
            dt = it.dt,
            icon = it.weatherDescription[0].icon,
            maxTemp = it.temp.max,
            minTemp = it.temp.min,
            daysTemp = it.temp.day,
            uvi = it.uvi,
            windSpeed = it.windSpeed
        )
    }.toTypedArray()
}