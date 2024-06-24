package com.dushanesmith.weather.data.repository

import com.dushanesmith.weather.data.weather.WeatherSave

interface WeatherSavesRepository{

    fun getAllSaves(): Array<WeatherSave>

    fun insertSave(save: WeatherSave)

    fun delete(save: WeatherSave)
}