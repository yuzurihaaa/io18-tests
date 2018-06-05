package com.miandroidchallenge.ucoppp.io18test.ui.main

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainFactoryViewModel @Inject constructor(val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ${modelClass.name} class")
    }
}