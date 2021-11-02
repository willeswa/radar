package com.wilies.radar

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

class MainViewModel(application: Application) : AndroidViewModel(application) {


    //hide the mutable response
    private val _response = MutableLiveData<WeatherResponse>()



    //immutable response exposed to the views
    val response: LiveData<WeatherResponse>
        get() = _response



    val currentWeather = Transformations.map(response){
        it.current.weather[0]
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
                    -1.3140785 ,36.8002512, "21de641062c535eadf648e52e2a263c0")
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