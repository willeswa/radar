package com.wilies.radar.ui.forecastscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.wilies.radar.database.getDatabase
import com.wilies.radar.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ForecastScreenViewModel(application: Application): AndroidViewModel(application){



    private val database = getDatabase(application)
    private val repository = WeatherRepository(database)

    private val job = Job()

    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)
    val isLoading = repository.isLoading

    init {
        coroutineScope.launch {
            repository.syncLocalWeatherWithServer(false)
            }
    }

    val dailyWeather = repository.dailyData
    val isInternetOn = repository.isInternetOn
    val tomorrowsWeather = Transformations.map(dailyWeather){
        it.getOrNull(0)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}