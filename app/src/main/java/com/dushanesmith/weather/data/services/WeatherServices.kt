package com.dushanesmith.weather.data.services

import com.dushane.weather.data.weather.WeatherResults
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//A interface for using latitude and longitude into weather information using openweatherapi
interface WeatherServices {

    @GET("onecall?units=imperial&appid=e301ed6982a42503a2653cb050e53db3")
    fun getResults(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Single<WeatherResults>
}