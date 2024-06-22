package com.dushanesmith.weather.data

import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.services.WeatherServices
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

//Repository for Home Screen View Model
class HomeRepository @Inject constructor(
    private val weatherServices: WeatherServices,
){
    fun getWeatherResults(lat: String, lon: String): Single<WeatherResults>{
        return weatherServices.getResults(lat, lon)
    }
}