package com.wilies.radar

import android.app.Application
import android.os.Build
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.wilies.radar.wokers.SyncWeatherWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar.HOUR
import java.util.concurrent.TimeUnit

class WeatherApplication: Application() {
    private val applicationScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch{
            syncWeatherData()
        }
    }



    private fun syncWeatherData() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
        
        val periodicSyncRequest = PeriodicWorkRequestBuilder<SyncWeatherWorker>(1,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            SyncWeatherWorker.WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicSyncRequest)
    }
}