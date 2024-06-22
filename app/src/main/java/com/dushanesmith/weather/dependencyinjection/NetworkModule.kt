package com.dushanesmith.weather.dependencyinjection

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//I wrote the modules in Java
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }

    @Singleton
    @Provides
    fun provideGSON(): Gson {
        return Gson().newBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "https://api.openweathermap.org/data/3.0/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String?
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}
