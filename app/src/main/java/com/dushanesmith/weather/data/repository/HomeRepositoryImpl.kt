package com.dushanesmith.weather.data.repository

import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.api.GeocodeAPI
import com.dushanesmith.weather.data.api.WeatherAPI
import com.dushanesmith.weather.data.location.LocationResults
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

//Repository for Home Screen View Model
class HomeRepositoryImpl @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val geocodeAPI: GeocodeAPI,
): HomeRepository {
    override fun getWeatherResults(lat: String, lon: String): Single<WeatherResults>{
        return weatherAPI.getResults(lat, lon)
    }

    override fun getLocationWithLatLng(latLng: String): Single<LocationResults> {
        return geocodeAPI.getLocationWithLatLng(latLng)
    }

    override fun getLocationWithAddress(address: String?): Single<LocationResults> {
        return geocodeAPI.getLocationWithAddress(address)
    }
}