package com.example.weatherapp.network.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    val temp: Double,
    @SerializedName("feels_like")
    val feelLike: Double,
    @SerializedName("temp_min")
    val minTemp: Double,
    @SerializedName("temp_max")
    val maxTemp: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val grndLevel: Int
) {

    fun getTempString(): String {
        return StringBuilder(temp.toInt().toString()).append("F").toString()
    }
}
