package com.dushanesmith.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dushanesmith.weather.data.weather.WeatherSave

@Database(entities = [WeatherSave::class], version = 1)
@TypeConverters(DailyWeatherTypeConverter::class)
abstract class WeatherSavesDatabase: RoomDatabase() {
    abstract fun weatherSaveDao(): WeatherSaveDao
}