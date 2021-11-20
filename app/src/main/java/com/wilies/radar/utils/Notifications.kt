package com.wilies.radar.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDeepLinkBuilder
import com.wilies.radar.MainActivity
import com.wilies.radar.R
import com.wilies.radar.WeatherApplication
import com.wilies.radar.network.DTODailyWeather
import com.wilies.radar.ui.dailyweatherscreen.DailyWeatherFragment
import kotlinx.metadata.internal.metadata.ProtoBuf

fun NotificationManager.sendNotification(weather: DTODailyWeather, loudly: Boolean = false){

    val tomorrowsWeatherPendingIntent = NavDeepLinkBuilder(WeatherApplication.APPLICATION_CONTEXT)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.SecondFragment)
        .createPendingIntent()


    val builder = NotificationCompat.Builder(
        WeatherApplication.APPLICATION_CONTEXT,
        WeatherApplication.APPLICATION_CONTEXT.getString(R.string.channel_id)
    ).apply {
        setSmallIcon(R.drawable.ic_launcher_foreground)
        setLargeIcon(Utility.getBitmap(weather))
        setContentTitle(Utility.getAddressFromLatLon())
        setContentIntent(tomorrowsWeatherPendingIntent)
        setSilent(loudly)
        setOngoing(true)
        setContentText(" ${Utility.toCelsius(weather.temp.day)}\u00B0 | ${weather.weatherDescription[0].description.toUpperCase()}")

    }

    notify(0, builder.build())
}