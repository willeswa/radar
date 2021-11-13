package com.wilies.radar.repository

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
import java.lang.Thread.sleep
import java.sql.Time

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

    val cacheCopyExists = Transformations.map(currentWeather){
        it != null
    }





    suspend fun refreshWeather() {
        withContext(Dispatchers.IO) {
        if(Utility.isInternetOn()){
            val weather = WeatherApi.retrofitService.getWeatherPredictions(
                -1.3140785,
                36.8002512,
                "bd6894074bc3be75555ebf7fa5ea4788"
            )
            database.weatherDao.deleteDailyWeather()
            database.weatherDao.deleteCurrentWeather()
            database.weatherDao.deleteHourlyWeather()
            database.weatherDao.insertCurrentWeather(weather.asCurrentWeatherDataModel())
            database.weatherDao.insertHourlyWeather(*weather.asHourlyWeatherDataModel())
            database.weatherDao.insertDailyWeather(*weather.asDailyWeatherDataModel())
        }else {
            isInternetOn.postValue(false)
        }
        }

        isLoading.postValue(false)

    }


}