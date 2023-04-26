package com.example.weatherapp.network.model

data class SystemInfo (
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
    )