package com.example.weatherapp.network

import com.example.weatherapp.network.model.City
import com.example.weatherapp.network.model.SummaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CallService {
    /**
     * Returns a detailed weather information for the location.
     */
    @GET("data/2.5/weather")
    suspend fun getWeatherInformation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "347bd60ef8361fe243881c47f587b3f1",
        @Query("units")units: String = "imperial"
    ): SummaryResponse

    /**
     * Returns a detailed weather information for the location.
     */
    @GET("geo/1.0/direct")
    suspend fun getCityList(
        @Query("q") query: String,
        @Query("limit")limint: Int = 5,
        @Query("appid") appid: String = "347bd60ef8361fe243881c47f587b3f1"
    ): List<City>
}