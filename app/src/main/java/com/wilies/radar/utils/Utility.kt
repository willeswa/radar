package com.wilies.radar.utils

import android.app.NotificationManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.net.ConnectivityManager
import android.util.Log
import com.wilies.radar.BuildConfig
import com.wilies.radar.WeatherApplication
import com.wilies.radar.domain.Coordinates
import com.wilies.radar.network.DTODailyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
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

    fun getAddressFromLatLon(): String{
        val geocode = Geocoder(WeatherApplication.APPLICATION_CONTEXT, Locale.getDefault())
        val addresses = geocode.getFromLocation(currentCoordinates().lat, currentCoordinates().lon, 1)

        addresses[0].apply {
            Log.i("ADDRESSSSS", "$locality, $subLocality, $adminArea, $subAdminArea, $featureName, $subThoroughfare")
            return when {
                featureName != null  -> "$featureName, $adminArea $countryName"
                subLocality != null ->  "$subLocality, $adminArea $countryName"
                locality != null -> "$locality, $adminArea"
                subAdminArea != null && subAdminArea != "Unnamed Road" -> "$subAdminArea, $countryName"
                else -> "$adminArea, $countryName"
            }
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
        if(currentNetwork != null){
            return true
        } else {
            throw Exception()
        }
    }

    fun notifyUserAboutUpdate(weather: DTODailyWeather, loudly: Boolean) {
        GlobalScope.launch (Dispatchers.Main){
            val notificationManager: NotificationManager = WeatherApplication.APPLICATION_CONTEXT.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.sendNotification(weather, loudly)
        }

    }

    fun getBitmap(weather: DTODailyWeather): Bitmap? {
        val url = URL(getIconUrl(weather.weatherDescription[0].icon))

        return try {
            BitmapFactory.decodeStream(url?.openConnection().getInputStream())
        } catch (e: Exception){
            null
        }
    }

    fun currentCoordinates(): Coordinates {
        return Coordinates(-1.3148,36.7938)

    }

    fun getApiKey(): String {
        return BuildConfig.API_KEY
    }

    fun getCurrentHourTime(): Int {
        val cal = Calendar.getInstance()
        Log.i("TAAG", ""+cal.get(Calendar.HOUR_OF_DAY))
        return cal.get(Calendar.HOUR_OF_DAY)
    }


}

