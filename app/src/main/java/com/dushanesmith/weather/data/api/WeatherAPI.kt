package com.dushanesmith.weather.data.api

import com.dushane.weather.data.weather.WeatherResults
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//A interface for using latitude and longitude into weather information using openweatherapi
interface WeatherAPI {

    @GET("onecall?exclude=minutely,hourly&units=imperial&appid=84c2b33596356c9fd60b69fc01a8afdf")
    fun getResults(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Single<WeatherResults>
}