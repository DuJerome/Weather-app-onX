package com.dushanesmith.weather.data

import com.dushane.weather.data.weather.WeatherResults
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    fun getWeatherResults(lat: String, lon: String): Single<WeatherResults>
}