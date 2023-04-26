package com.example.weatherapp.network.model

data class City (
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
    )