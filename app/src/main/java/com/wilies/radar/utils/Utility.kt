package com.wilies.radar.utils

import android.net.ConnectivityManager
import com.wilies.radar.WeatherApplication
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    private lateinit var application: WeatherApplication

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

    fun isInternetOn(): Boolean {
        val networkManager = WeatherApplication.APPLICATION_CONTEXT.getSystemService(
            ConnectivityManager::class.java)
        val currentNetwork = networkManager.activeNetwork
        return currentNetwork != null
    }
}

