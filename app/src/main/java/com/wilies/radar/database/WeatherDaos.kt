package com.wilies.radar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wilies.radar.database.models.*
import retrofit2.http.DELETE

@Dao
interface WeatherDao{

    @Transaction
    @Query("SELECT * FROM current_weather")
    fun getCurrentWeather(): LiveData<List<CurrentWeatherWithDescription>>

    @Query("SELECT * FROM current_weather ORDER BY dt ASC")
    fun getHourlyWeather(): LiveData<List<WeatherEntity>>

    @Query("SELECT * FROM daily_weathers")
    fun getDailyWeather(): LiveData<List<DailyWeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemp(vararg temps: TempEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(vararg weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(vararg weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(vararg weather: DailyWeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherDescription(vararg desc: WeatherDescriptionEntity)

    @Query("DELETE FROM current_weather")
    fun deleteWeather()

    @Query("DELETE FROM daily_weathers")
    fun deleteDailyWeather()

    @Query("DELETE FROM hourly_weather")
    fun deleteHourlyWeather()





}