package com.miandroidchallenge.ucoppp.io18test.models.weatherforecast

import com.google.gson.annotations.SerializedName

data class Weathers(
    @SerializedName("cod") val cod: String,
    @SerializedName("message") val message: Double,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<X>,
    @SerializedName("city") val city: City
)