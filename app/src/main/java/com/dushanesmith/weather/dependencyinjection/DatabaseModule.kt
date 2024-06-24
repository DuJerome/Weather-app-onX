package com.dushanesmith.weather.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.dushanesmith.weather.data.database.WeatherSavesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherSavesDB(@ApplicationContext context: Context) : WeatherSavesDatabase {
        return Room.databaseBuilder(
            context,
            WeatherSavesDatabase::class.java, "WeatherSavesDatabase"
        )
            .allowMainThreadQueries()
            .build()
    }

}