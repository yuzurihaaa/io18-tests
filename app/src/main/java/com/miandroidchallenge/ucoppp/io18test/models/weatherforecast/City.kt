package com.miandroidchallenge.ucoppp.io18test.models.weatherforecast

import com.google.gson.annotations.SerializedName
data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: Coord,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int
)