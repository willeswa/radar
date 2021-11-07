package com.wilies.radar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wilies.radar.database.WeatherDatabase
import com.wilies.radar.database.asDomainModel
import com.wilies.radar.database.models.WeatherEntity
import com.wilies.radar.database.toHourlyDomain
import com.wilies.radar.domain.Weather
import com.wilies.radar.domain.WeatherWithDescription
import com.wilies.radar.network.WeatherApi
import com.wilies.radar.network.asDailyDataModel
import com.wilies.radar.network.asDatabaseModel
import com.wilies.radar.network.asHourlyDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    val currentWeather: LiveData<List<WeatherWithDescription>> = Transformations.map(database.weatherDao.getCurrentWeather()){
        it.asDomainModel()
    }

    val hourlyData: LiveData<List<Weather>> = Transformations.map(database.weatherDao.getHourlyWeather()){
        it.toHourlyDomain()
    }







    suspend fun refreshWeather() {
        withContext(Dispatchers.IO){

            val weather = WeatherApi.retrofitService.getWeatherPredictions(-1.3140785 ,36.8002512, "eae24bf8b5610f12c69dfc270622552e")
            database.weatherDao.insertWeather(weather.current.asDatabaseModel())
            database.weatherDao.insertDailyWeather(*weather.daily.asDailyDataModel())
            database.weatherDao.insertHourlyWeather(*weather.hourly.asHourlyDataModel())
        }
    }


}