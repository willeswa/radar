package com.wilies.radar.dailyweatherscreen

import android.app.Application
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.wilies.radar.R
import com.wilies.radar.WeatherApi
import com.wilies.radar.data.models.WeatherResponse
import com.wilies.radar.utils.Utility
import com.wilies.radar.utils.WeatherDescriptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DailyWeatherViewModel(application: Application) : AndroidViewModel(application) {


    //hide the mutable response
    private val _response = MutableLiveData<WeatherResponse>()

    val hourly_predictions = Transformations.map(_response){
        it.hourly
    }

    //immutable response exposed to the views
    val response: LiveData<WeatherResponse>
        get() = _response


    val currentPropertyDate = Transformations.map(response) {
        Utility.getDateTime(it.current.dt)
    }

    val currentWeather = Transformations.map(response){
        it.current.weather[0]
    }

    val tempInCelsius = Transformations.map(_response){
        it.current.temp - 273.15
    }



    //a job to manage the coroutines
    private val dailyWeatherJob = Job()

    // a coroutine scope to handle the network execution
    private val coroutineScope = CoroutineScope(dailyWeatherJob + Dispatchers.Main)


    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        coroutineScope.launch {
            var getWeatherDeferred = WeatherApi.retrofitService.getWeatherPredictions(
                    0.660450, 28.761551, "21de641062c535eadf648e52e2a263c0")
            try {
                _response.value = getWeatherDeferred
            } catch (ex: Exception) {
                Log.i("TAG", "" + ex.message)
            }


        }
    }

    override fun onCleared() {
        super.onCleared()
        dailyWeatherJob.cancel()
    }
}