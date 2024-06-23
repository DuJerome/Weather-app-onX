package com.dushanesmith.weather.dependencyinjection

import com.dushanesmith.weather.HomeViewModel
import com.dushanesmith.weather.data.HomeRepository
import com.dushanesmith.weather.data.HomeRepositoryImpl
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
    fun provideHomeRepository(
        weatherAPI: WeatherAPI,
    ): HomeRepository {
        return HomeRepositoryImpl(weatherAPI)
    }

    @Singleton
    @Provides
    fun provideHomeViewModel(homeRepositoryImpl: HomeRepositoryImpl): HomeViewModel {
        return HomeViewModel(homeRepositoryImpl)
    }
}
