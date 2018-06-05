package com.miandroidchallenge.ucoppp.io18test.ui.main

import android.animation.ValueAnimator
import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentCallbacks2
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.github.matteobattilana.weather.PrecipType
import com.github.matteobattilana.weather.WeatherViewSensorEventListener
import com.miandroidchallenge.ucoppp.io18test.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.math.cos
import kotlin.math.sin


class MainFragment : Fragment(), ComponentCallbacks2 {

    private val y = 400F
    private val x = 0F

    private lateinit var weatherSensor: WeatherViewSensorEventListener

    private val factory by lazy {
        MainFactoryViewModel(
                application = activity!!.application
        )
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val valueAnimator = ValueAnimator.ofFloat(2.2f, -1.5F)
        val valueAnimator2 = ValueAnimator.ofFloat(2.2f, -0.5F)
        val valueAnimator3 = ValueAnimator.ofFloat(2.2f, 0.5F)
        val valueAnimator4 = ValueAnimator.ofFloat(2.2f, 1.5F)

        setAnimation(
                arrayOf(valueAnimator, valueAnimator2, valueAnimator3, valueAnimator4),
                arrayOf(weather_1, weather_2, weather_3, weather_4)
        )

        Handler().postDelayed({
            setAnimation(
                    arrayOf(valueAnimator, valueAnimator2, valueAnimator3, valueAnimator4),
                    arrayOf(weather_1, weather_2, weather_3, weather_4),
                    reverse = true
            )
        }, 2000)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherSensor = WeatherViewSensorEventListener(activity?.applicationContext!!, weather_view)

        weather_view.fadeOutPercent = 0.75F
        weather_view.precipType = PrecipType.RAIN
        weather_view.speed = 1000
        weather_view.emissionRate = 100F

        weatherSensor.stop()


        viewModel.getCurrentWeather()
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
                weatherSensor.stop()
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

    private fun setAnimation(valueAnimators: Array<ValueAnimator>, view: Array<View>, reverse: Boolean = false) {
        for (pos: Int in 0 until valueAnimators.size) {

            valueAnimators[pos].addUpdateListener {
                val value = valueAnimators[pos].animatedValue as Float
                val axisX = x + cos(value) * 350
                val axisY = y + sin(value) * 350
                view[pos].translationX = axisX
                view[pos].translationY = axisY
            }
            valueAnimators[pos].interpolator = DecelerateInterpolator()
            valueAnimators[pos].duration = 1000
            if (reverse) valueAnimators[pos].reverse() else valueAnimators[pos].start()

        }
    }
}