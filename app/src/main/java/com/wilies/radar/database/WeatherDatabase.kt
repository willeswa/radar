package com.wilies.radar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wilies.radar.database.models.*

@Database(
    entities = [TempEntity::class,
        WeatherEntity::class,
        WeatherDescriptionEntity::class,
        DailyWeatherEntity::class,
        HourlyWeatherEntity::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "videos"
            ).build()
        }
    }
    return INSTANCE
}