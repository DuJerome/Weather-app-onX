package com.dushanesmith.weather.data.api

import com.dushanesmith.weather.data.location.LocationResults
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeAPI {
    @GET("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDgTcqSwMpIRTRTJV7D2sV9yZXEejIYmNM")
    fun getLocationWithLatLng(
        @Query("latlng") latlng: String
    ): Single<LocationResults>

    @GET("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDgTcqSwMpIRTRTJV7D2sV9yZXEejIYmNM")
    fun getLocationWithAddress(
        @Query("address") address: String?
    ): Single<LocationResults>
}