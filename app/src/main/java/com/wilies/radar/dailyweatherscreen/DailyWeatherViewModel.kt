package com.wilies.radar.dailyweatherscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wilies.radar.WeatherApi
import com.wilies.radar.data.models.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyWeatherViewModel: ViewModel(){


    private val _response = MutableLiveData<WeatherResponse>()

    private val dailyWeatherJob = Job()

    private val coroutineScope = CoroutineScope(dailyWeatherJob + Dispatchers.Main)

    val response: LiveData<WeatherResponse>
        get() = _response


    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        coroutineScope.launch {
            var getWeatherDeferred = WeatherApi.retrofitService.getWeatherPredictions(
                    0.660450, 28.761551, "21de641062c535eadf648e52e2a263c0")

            try {

                _response.value = getWeatherDeferred
            } catch (ex: Exception){
                Log.i("TAG", ""+ex.message)
            }


        }
    }

    override fun onCleared() {
        super.onCleared()
        dailyWeatherJob.cancel()
    }
}