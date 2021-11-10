package com.wilies.radar.wokers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.wilies.radar.database.getDatabase
import com.wilies.radar.repository.WeatherRepository
import retrofit2.HttpException

class SyncWeatherWorker(private val context: Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams) {
    companion object {
        const val WORKER_NAME = "sync_weather_worker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(context)
        val repository = WeatherRepository(database)

        return try {
            repository.refreshWeather()
            Result.success()
        } catch (e: HttpException){
            Result.retry()
        }
    }

}