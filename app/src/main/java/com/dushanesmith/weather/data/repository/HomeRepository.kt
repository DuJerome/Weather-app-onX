package com.dushanesmith.weather.data.repository

import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.location.LocationResults
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    fun getWeatherResults(lat: String, lon: String): Single<WeatherResults>
    fun getLocationWithLatLng(latLng: String): Single<LocationResults>
    fun getLocationWithAddress(address: String? = null): Single<LocationResults>
}