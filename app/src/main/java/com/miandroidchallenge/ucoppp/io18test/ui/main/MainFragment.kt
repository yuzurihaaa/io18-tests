package com.miandroidchallenge.ucoppp.io18test.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.matteobattilana.weather.PrecipType
import com.github.matteobattilana.weather.WeatherViewSensorEventListener
import com.miandroidchallenge.ucoppp.io18test.R
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment(), ComponentCallbacks2 {

    lateinit var weatherSensor: WeatherViewSensorEventListener

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        weatherSensor = WeatherViewSensorEventListener(activity?.applicationContext!!, weather_view)

        weather_view.fadeOutPercent = 0.75F
        weather_view.precipType = PrecipType.RAIN
        weather_view.speed = 50
        weather_view.emissionRate = 10F

    }

    override fun onTrimMemory(level: Int) {
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE,
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
                weatherSensor.onPause()
            }

            else -> weatherSensor.start()
        }
    }

    override fun onResume() {
        super.onResume()
        weatherSensor.onResume()
    }

    override fun onPause() {
        super.onPause()
        weatherSensor.onPause()
    }
}
