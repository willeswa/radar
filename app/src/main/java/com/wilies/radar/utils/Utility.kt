package com.wilies.radar.utils

import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun getFormattedDateTime(timestamp: Double, dateFormat: String):String{
        return try {
            val simpleFormat = SimpleDateFormat(dateFormat)
            val newDate = Date(timestamp.toLong()*1000)
            simpleFormat.format(newDate)
        } catch (e: Exception){
            e.toString()
        }
    }

    fun getTimeOfTheDay(timestamp: Double): String?{
        return try {
            val simpleFormat = SimpleDateFormat("h a")
            val newDate = Date(timestamp.toLong()*1000)
            simpleFormat.format(newDate)
        } catch (e: Exception){
            e.toString()
        }
    }

    fun toCelsius(temp: Double) = (temp - 273).toInt().toString()




    fun getIconUrl(rainIcon: String): String?{
        return "http://openweathermap.org/img/wn/$rainIcon@2x.png"
    }
}

