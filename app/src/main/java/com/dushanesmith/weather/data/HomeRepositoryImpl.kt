package com.dushanesmith.weather.data

import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.api.WeatherAPI
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

//Repository for Home Screen View Model
class HomeRepositoryImpl @Inject constructor(
    private val weatherAPI: WeatherAPI,
): HomeRepository{
    override fun getWeatherResults(lat: String, lon: String): Single<WeatherResults>{
        return weatherAPI.getResults(lat, lon)
    }
}