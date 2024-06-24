package com.dushanesmith.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.repository.HomeRepository
import com.dushanesmith.weather.data.repository.WeatherSavesRepository
import com.dushanesmith.weather.data.weather.WeatherSave
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// View Model for homescreen, it gets latitude and longitude and retrieves weather data.
// Provides state information for the compose Ui views to use
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val weatherSavesRepository: WeatherSavesRepository
): ViewModel() {
    private var _currentWeatherResults: MutableStateFlow<WeatherResults> = MutableStateFlow(WeatherResults())

    var currentWeatherResults: StateFlow<WeatherResults> = _currentWeatherResults
    var currentLocationCity : String? = null

    fun getWeatherResults(lat: String, lon: String): WeatherResults {
        val locationWithAddress = homeRepository.getLocationWithLatLng("$lat, $lon").blockingGet()
        val locationCity = locationWithAddress.results[0].addressComponents.find { it.types.contains("administrative_area_level_2") }?.longName
        currentLocationCity = locationCity
        val cityLng = homeRepository.getLocationWithAddress(locationCity).blockingGet().results[0].geometry?.location?.lng
        val cityLat = homeRepository.getLocationWithAddress(locationCity).blockingGet().results[0].geometry?.location?.lng
        val data = homeRepository.getWeatherResults(cityLat.toString(), cityLng.toString()).blockingGet()
        _currentWeatherResults.compareAndSet(
            currentWeatherResults.value,
            data
        )
        return data
    }

    fun getAllSaves(): Array<WeatherSave> {
        return weatherSavesRepository.getAllSaves()
    }

    fun insertSave(save: WeatherSave) {
        weatherSavesRepository.insertSave(save)
    }
}