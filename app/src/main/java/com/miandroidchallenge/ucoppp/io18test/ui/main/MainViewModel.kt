package com.miandroidchallenge.ucoppp.io18test.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import com.miandroidchallenge.ucoppp.io18test.di.MyApplication
import com.miandroidchallenge.ucoppp.io18test.models.ErrorResponse
import com.miandroidchallenge.ucoppp.io18test.models.weather.WeatherCoordiate
import com.miandroidchallenge.ucoppp.io18test.ui.main.api.WeatherApi
import com.miandroidchallenge.ucoppp.io18test.util.Listener
import com.miandroidchallenge.ucoppp.io18test.util.RetrofitRequest
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    @Inject
    lateinit var retrofit: Retrofit

    init {
        (application as MyApplication).appComponent.inject(this)
    }

    fun getCurrentWeather(): Disposable {
        val api: WeatherApi = retrofit.create(WeatherApi::class.java)

        return RetrofitRequest(application = getApplication())
                .makeJSONRequest(
                        api.getWeathersByCoordicate("-1135", "139"),
                        object : Listener<WeatherCoordiate, ErrorResponse> {
                            override fun onPreRequest() {
                                Log.e("Pre request", "Pre request")
                            }

                            override fun onResponse(`object`: WeatherCoordiate) {
                                Log.e("onResponse", `object`.weather[0].description)
                            }

                            override fun onError(error: ErrorResponse) {
                                Log.e("onError", error.message)
                            }

                            override fun onDeviceError(error: String) {
                                Log.e("onError", error)
                            }
                        }
                )
    }

}
