package com.miandroidchallenge.ucoppp.io18test.models.weatherforecast

import com.google.gson.annotations.SerializedName
data class Coord(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)