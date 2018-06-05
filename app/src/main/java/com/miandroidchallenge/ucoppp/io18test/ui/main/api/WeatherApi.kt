package com.miandroidchallenge.ucoppp.io18test.ui.main.api

import com.miandroidchallenge.ucoppp.io18test.models.weather.WeatherCoordiate
import com.miandroidchallenge.ucoppp.io18test.models.weatherforecast.Weathers
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?appid=f5494a7c084b83729a1ef81f70d182f6&")
    fun getForecastWeathers(
            @Query("q") location: String)
            : Observable<Weathers>

    @GET("data/2.5/weather?appid=f5494a7c084b83729a1ef81f70d182f6")
    fun getWeathersByCoordicate(
            @Query("lat") latitued: String,
            @Query("lon") longitude: String)
            : Observable<WeatherCoordiate>
}