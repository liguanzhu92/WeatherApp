package com.example.weatherapp.data

import com.example.weatherapp.CallResult
import com.example.weatherapp.network.CallService
import com.example.weatherapp.network.model.City
import com.example.weatherapp.network.model.SummaryResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor (private val service: CallService) {

    suspend fun getWeatherInfo(lat: Double, lon: Double): CallResult<SummaryResponse> {
        return try {
            val response = service.getWeatherInformation(lat, lon)
            CallResult.Success(response)
        } catch (e: Exception) {
            CallResult.Error(e.message)
        }
    }

    suspend fun getCityList(city: String, state: String? = null, country: String? = null): CallResult<List<City>> {
        val sb = StringBuilder(city)
        state?.let {
            sb.append(it)
        }
        country?.let {
            sb.append(it)
        }
        return try {
            val response = service.getCityList(sb.toString())
            CallResult.Success(response)
        } catch (e: Exception) {
            CallResult.Error(e.message)
        }
    }
}