package com.wilies.radar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.wilies.radar.database.WeatherDatabase
import com.wilies.radar.database.asCurrentWeatherDomain
import com.wilies.radar.database.asDailyWeatherDomain
import com.wilies.radar.database.asHourlyWeatherDomain
import com.wilies.radar.domain.CurrentWeatherDomain
import com.wilies.radar.domain.DailyWeatherDomain
import com.wilies.radar.domain.HourlyWeatherDomain
import com.wilies.radar.network.WeatherApi
import com.wilies.radar.network.asCurrentWeatherDataModel
import com.wilies.radar.network.asDailyWeatherDataModel
import com.wilies.radar.network.asHourlyWeatherDataModel
import com.wilies.radar.utils.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    val isInternetOn: MutableLiveData<Boolean> = MutableLiveData(true)


    val currentWeather: LiveData<CurrentWeatherDomain?> =
        Transformations.map(database.weatherDao.getCurrentWeather()) {
            it?.asCurrentWeatherDomain()
        }

    val hourlyData: LiveData<List<HourlyWeatherDomain?>> =
        Transformations.map(database.weatherDao.getHourlyWeather()) {
            it.asHourlyWeatherDomain()
        }


    val dailyData: LiveData<List<DailyWeatherDomain?>> =
        Transformations.map(database.weatherDao.getDailyWeather()) {
            it.asDailyWeatherDomain()
        }


    suspend fun syncLocalWeatherWithServer(mainLoad: Boolean) {

     withContext(Dispatchers.IO){
         try {
             if(mainLoad){
                 Utility.isInternetOn()
                 val weather = WeatherApi.retrofitService.getWeatherPredictions(
                     Utility.currentCoordinates().lat,
                     Utility.currentCoordinates().lon,
                     Utility.getApiKey()
                 )
                 database.weatherDao.deleteDailyWeather()
                 database.weatherDao.deleteCurrentWeather()
                 database.weatherDao.deleteHourlyWeather()
                 database.weatherDao.insertCurrentWeather(weather.asCurrentWeatherDataModel())
                 database.weatherDao.insertHourlyWeather(*weather.asHourlyWeatherDataModel())
                 database.weatherDao.insertDailyWeather(*weather.asDailyWeatherDataModel())
                 if(Utility.getCurrentHourTime() == 20){
                     Utility.notifyUserAboutUpdate(weather.daily[0], false)
                 } else {
                     Utility.notifyUserAboutUpdate(weather.daily[0], true)
                 }
             }
         } catch (e: Exception){
             isInternetOn.postValue(false)

         } finally {
             isLoading.postValue(false)
         }
     }

    }



}