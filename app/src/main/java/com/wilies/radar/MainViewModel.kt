package com.wilies.radar

import android.app.Application
import androidx.lifecycle.*
import com.wilies.radar.database.getDatabase
import com.wilies.radar.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {




    //a job to manage the coroutines
    private val dailyWeatherJob = Job()

    // a coroutine scope to handle the network execution
    private val coroutineScope = CoroutineScope(dailyWeatherJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = WeatherRepository(database)

    init {
        coroutineScope.launch {
            repository.refreshWeather()
        }
    }

    val currentWeather = repository.currentWeather
    val hourlyWeather = repository.hourlyData
    val dailyWeather = repository.dailyData

    val isLoading: LiveData<Boolean> = Transformations.map(dailyWeather){
     it.isEmpty()
    }




    override fun onCleared() {
        super.onCleared()
        dailyWeatherJob.cancel()
    }
}