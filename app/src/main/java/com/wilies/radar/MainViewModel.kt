package com.wilies.radar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wilies.radar.database.getDatabase
import com.wilies.radar.repository.WeatherRepository
import com.wilies.radar.utils.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


class MainViewModel(application: Application) : AndroidViewModel(application) {



    //a job to manage the coroutines
    private val dailyWeatherJob = Job()

    // a coroutine scope to handle the network execution
    private val coroutineScope = CoroutineScope(dailyWeatherJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = WeatherRepository(database)
    lateinit var cacheCopyExists: LiveData<Boolean>
    var isLoading = repository.isLoading


    init {

            coroutineScope.launch {
                repository.refreshWeather()
            }
    }

    val currentWeather = repository.currentWeather
    val hourlyWeather = repository.hourlyData
    val dailyWeather = repository.dailyData
    val isInternetOn = repository.isInternetOn










    override fun onCleared() {
        super.onCleared()
        dailyWeatherJob.cancel()
    }
}