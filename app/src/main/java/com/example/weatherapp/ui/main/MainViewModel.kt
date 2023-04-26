package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.CallResult
import com.example.weatherapp.Constants.IMAGE_BASE_URL
import com.example.weatherapp.data.DataRepository
import com.example.weatherapp.network.model.City
import com.example.weatherapp.network.model.SummaryResponse
import com.example.weatherapp.network.model.Weather
import com.example.weatherapp.utils.SPManager
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

class MainViewModel @Inject constructor (private val repository: DataRepository): ViewModel()  {
    private val _cityListLivedata = MutableLiveData<List<City>>()
    @Inject lateinit var spManager: SPManager

    fun getCityList(query: String) {
        viewModelScope.launch {
            when (val result = repository.getCityList(query)) {
                is CallResult.Success<List<City>> -> {
                    _cityListLivedata.value = result.data
                }
                is CallResult.Error -> {
                    //no-op
                }
            }
        }
    }

    fun updateLastCity(city: String) {
        spManager.addLastAccessCity(city)
    }

    fun getLastCity(): String {
        return spManager.getLastAccessCity()
    }

    fun clearList() {
        _cityListLivedata.value = listOf()
    }

    val cityListLivedata: LiveData<List<City>>
        get() = _cityListLivedata


    private val _weatherLivedata = MutableLiveData<SummaryResponse>()

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            when (val result = repository.getWeatherInfo(
                lat, lon)) {
                is CallResult.Success<SummaryResponse> -> {
                    _weatherLivedata.value = result.data
                }
                is CallResult.Error -> {
                    //no-op
                }
            }
        }
    }

    val weatherLivedata: LiveData<SummaryResponse>
        get() = _weatherLivedata

    fun getWeatherIcon(weather: Weather): String {
        return StringBuilder(IMAGE_BASE_URL).append(weather.icon).append("@4x.png").toString()
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}