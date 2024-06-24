package com.dushanesmith.weather.dependencyinjection

import com.dushanesmith.weather.viewmodel.HomeViewModel
import com.dushanesmith.weather.data.repository.HomeRepository
import com.dushanesmith.weather.data.repository.HomeRepositoryImpl
import com.dushanesmith.weather.data.database.WeatherSavesDatabase
import com.dushanesmith.weather.data.repository.WeatherSavesRepository
import com.dushanesmith.weather.data.repository.WeatherSavesRepositoryImpl
import com.dushanesmith.weather.data.api.GeocodeAPI
import com.dushanesmith.weather.data.api.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeViewModelModule {
    @Singleton
    @Provides
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideGeocodeAPI(retrofit: Retrofit): GeocodeAPI {
        return retrofit.create(GeocodeAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        weatherAPI: WeatherAPI,
        geocodeAPI: GeocodeAPI
    ): HomeRepository {
        return HomeRepositoryImpl(weatherAPI, geocodeAPI)
    }

    @Singleton
    @Provides
    fun provideWeatherSavesRepository(weatherSavesDatabase: WeatherSavesDatabase): WeatherSavesRepository {
        return WeatherSavesRepositoryImpl(weatherSavesDatabase)
    }

    @Singleton
    @Provides
    fun provideHomeViewModel(homeRepository: HomeRepository, weatherSavesRepository: WeatherSavesRepository): HomeViewModel {
        return HomeViewModel(homeRepository, weatherSavesRepository)
    }
}
