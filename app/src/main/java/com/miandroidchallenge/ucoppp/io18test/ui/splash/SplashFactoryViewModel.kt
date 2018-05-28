package com.miandroidchallenge.ucoppp.io18test.ui.splash

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class SplashFactoryViewModel @Inject constructor(val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ${modelClass.name} class")
    }
}