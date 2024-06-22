package com.dushanesmith.weather.dependencyinjection

import com.dushanesmith.weather.HomeViewModel
import com.dushanesmith.weather.data.HomeRepository
import com.dushanesmith.weather.data.services.WeatherServices
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
    fun provideWeatherServices(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        weatherServices: WeatherServices,
    ): HomeRepository {
        return HomeRepository(weatherServices)
    }

    @Singleton
    @Provides
    fun provideHomeViewModel(homeRepository: HomeRepository): HomeViewModel {
        return HomeViewModel(homeRepository)
    }
}
