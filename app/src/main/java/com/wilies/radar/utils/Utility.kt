package com.wilies.radar.utils

import com.wilies.radar.data.models.Weather
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun getDateTime(timestamp: Double):String?{
        return try {
            val simpleFormat = SimpleDateFormat("EEE, MMM yyyy")
            val newDate = Date(timestamp.toLong()*1000)
            simpleFormat.format(newDate)
        } catch (e: Exception){
            e.toString()
        }
    }

    fun toCelsius(temp: Double) = (temp - 273).toInt().toString()


    fun setIconForWeather(current: Weather): String? {

        return when(current.weather[0].description.toLowerCase()){
            WeatherDescriptions.RAIN -> getIconUrl(WeatherIcons.RAIN_ICON)
            WeatherDescriptions.BROKEN_CLOUDS -> getIconUrl(WeatherIcons.BROKEN_CLOUDS_ICON)
            WeatherDescriptions.CLEAR_SKIES -> getIconUrl(WeatherIcons.CLEAR_SKIES_ICON)
            WeatherDescriptions.FEW_CLOUDS -> getIconUrl(WeatherIcons.FEW_CLOUDS_ICON)
            WeatherDescriptions.MIST -> getIconUrl(WeatherIcons.MIST_ICON)
            WeatherDescriptions.SHOWER_RAINS -> getIconUrl(WeatherIcons.SHOWER_RAINS_ICON)
            WeatherDescriptions.SNOW -> getIconUrl(WeatherIcons.SNOW_ICON)
            WeatherDescriptions.SCATTERED_CLOUDS -> getIconUrl(WeatherIcons.SCATTERED_CLOUDS_ICON)
            else -> getIconUrl(WeatherIcons.THUNDERSTORM_ICON)
        }
    }

    fun getIconUrl(rainIcon: String): String?{
        return "http://openweathermap.org/img/wn/$rainIcon@2x.png"
    }
}

