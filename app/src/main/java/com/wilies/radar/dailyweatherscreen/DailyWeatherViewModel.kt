package com.wilies.radar.dailyweatherscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wilies.radar.WeatherApi
import com.wilies.radar.data.WeatherRepository
import com.wilies.radar.data.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyWeatherViewModel: ViewModel(){


    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response


    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        WeatherApi.retrofitService.getWeatherPredictions(0.660450, 28.761551, "API_KEY").enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("DailyWeatherViewModel", ""+response.body())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("DailyWeatherViewModel", "Nopew")
            }

        })
    }

}