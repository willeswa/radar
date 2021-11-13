package com.wilies.radar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wilies.radar.database.models.*
import retrofit2.http.DELETE

@Dao
interface WeatherDao{

    @Query("SELECT * FROM current_weathers LIMIT 1")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>

    @Query("SELECT * FROM hourly_weathers")
    fun getHourlyWeather(): LiveData<List<HourlyWeatherEntity>>

    @Query("SELECT * FROM daily_weather")
    fun getDailyWeather(): LiveData<List<DailyWeatherEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(vararg currentWeather: CurrentWeatherEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(vararg currentWeather: HourlyWeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(vararg currentWeather: DailyWeatherEntity)

    @Query("DELETE FROM current_weathers")
    fun deleteCurrentWeather()


    @Query("DELETE FROM hourly_weathers ")
    fun deleteHourlyWeather()

    @Query("DELETE FROM daily_weather")
    fun deleteDailyWeather()

}