package com.wilies.radar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wilies.radar.database.*
import com.wilies.radar.domain.*
import com.wilies.radar.network.WeatherApi
import com.wilies.radar.network.asCurrentWeatherDataModel
import com.wilies.radar.network.asDailyWeatherDataModel
import com.wilies.radar.network.asHourlyWeatherDataModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    val currentWeather: LiveData<List<CurrentWeatherDomain>> = Transformations.map(database.weatherDao.getCurrentWeather()){
        it.asCurrentWeatherDomain()
    }

    val hourlyData: LiveData<List<HourlyWeatherDomain>> = Transformations.map(database.weatherDao.getHourlyWeather()){
        it.asHourlyWeatherDomain()
    }


    val dailyData: LiveData<List<DailyWeatherDomain>> = Transformations.map(database.weatherDao.getDailyWeather()){
        it.asDailyWeatherDomain()
    }







    suspend fun refreshWeather() {
        withContext(Dispatchers.IO){
            val weather = WeatherApi.retrofitService.getWeatherPredictions(-1.3140785 ,36.8002512, "")
            database.weatherDao.insertCurrentWeather(weather.asCurrentWeatherDataModel())
            database.weatherDao.insertHourlyWeather(*weather.asHourlyWeatherDataModel())
            database.weatherDao.insertDailyWeather(*weather.asDailyWeatherDataModel())
        }
    }


}