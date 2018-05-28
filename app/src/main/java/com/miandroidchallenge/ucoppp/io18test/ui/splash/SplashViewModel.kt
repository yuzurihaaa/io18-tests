package com.miandroidchallenge.ucoppp.io18test.ui.splash

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.miandroidchallenge.ucoppp.io18test.di.MyApplication
import javax.inject.Inject

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    var testLiveData: MutableLiveData<String> = MutableLiveData()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {

        (application as MyApplication).appComponent.inject(this)

        sharedPreferences.edit().putString("test", "value").apply()

        testLiveData.postValue(sharedPreferences.getString("test", ""))
    }
}
