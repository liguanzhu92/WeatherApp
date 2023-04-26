package com.example.weatherapp.network.model

import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    val id: Long,
    val name: String,
    val dt: Long,
    val timezone: Int,
    @SerializedName("sys")
    val info: SystemInfo,
    @SerializedName("main")
    val tempInfo:Temperature,
    val weather: List<Weather>,
    @SerializedName("coord")
    val location: Coord
)
