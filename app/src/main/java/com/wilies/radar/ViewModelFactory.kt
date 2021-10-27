package com.wilies.radar

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wilies.radar.dailyweatherscreen.DailyWeatherViewModel
import com.wilies.radar.data.WeatherRepository

//class ViewModelFactory(
//        private val weatherRepository: WeatherRepository,
//        private val application: Application
//): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>) = {
//        with(modelClass){
//            when{
//                isAssignableFrom(DailyWeatherViewModel::class.java) ->
//                    DailyWeatherViewModel(weatherRepository, application)
//                else ->
//                    throw IllegalArgumentException("")
//            }
//        }
//    } as T
//}

