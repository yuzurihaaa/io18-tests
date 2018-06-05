package com.miandroidchallenge.ucoppp.io18test.models.weatherforecast

import com.google.gson.annotations.SerializedName

data class Rain(
        @SerializedName("3h") val h: Double
)