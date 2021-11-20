package com.wilies.radar

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.*
import com.wilies.radar.wokers.SyncWeatherWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class WeatherApplication: Application() {
    private val applicationScope = CoroutineScope(Dispatchers.IO)

    companion object {
        lateinit var APPLICATION_CONTEXT: Application
    }


    override fun onCreate() {
        super.onCreate()
        applicationScope.launch{
            syncWeatherData()
        }

        APPLICATION_CONTEXT = this
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name_weather_sync)
            var description = getString(R.string.channel_description_weather_sync)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                description = description
                }


            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun syncWeatherData() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
        
        val periodicSyncRequest = PeriodicWorkRequestBuilder<SyncWeatherWorker>(1,
            TimeUnit.HOURS,
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            SyncWeatherWorker.WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicSyncRequest)
    }
}