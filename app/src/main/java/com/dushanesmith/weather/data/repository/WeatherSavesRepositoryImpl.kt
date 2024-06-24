package com.dushanesmith.weather.data.repository

import com.dushanesmith.weather.data.database.WeatherSavesDatabase
import com.dushanesmith.weather.data.weather.WeatherSave
import javax.inject.Inject

class WeatherSavesRepositoryImpl @Inject constructor(
    val weatherSavesDatabase: WeatherSavesDatabase,
): WeatherSavesRepository {
    override fun getAllSaves(): Array<WeatherSave> {
        return weatherSavesDatabase.weatherSaveDao().getAll()
    }

    override fun insertSave(save: WeatherSave) {
        weatherSavesDatabase.weatherSaveDao().insert(save)
    }


}