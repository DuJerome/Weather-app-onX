package com.dushanesmith.weather

import androidx.lifecycle.ViewModel
import com.dushane.weather.data.weather.WeatherResults
import com.dushanesmith.weather.data.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// View Model for homescreen, it gets latitude and longitude and retrieves weather data.
// Provides state information for the compose Ui views to use
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    private var _currentWeatherResults: MutableStateFlow<WeatherResults> = MutableStateFlow(WeatherResults())

    var currentWeatherResults: StateFlow<WeatherResults> = _currentWeatherResults

    fun getWeatherResults(lat: String, lon: String): WeatherResults {
        val data = homeRepository.getWeatherResults(lat, lon).blockingGet()
        _currentWeatherResults.compareAndSet(
            currentWeatherResults.value,
            data
        )
        return data
    }
}