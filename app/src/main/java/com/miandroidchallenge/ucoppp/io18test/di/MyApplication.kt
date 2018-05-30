package com.miandroidchallenge.ucoppp.io18test.di

import android.app.Activity
import android.app.Application
import android.app.Fragment
import com.miandroidchallenge.ucoppp.io18test.modules.ApiModule
import com.miandroidchallenge.ucoppp.io18test.modules.UserModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import android.support.v4.app.Fragment as SupportFragment

class MyApplication : Application(), HasActivityInjector, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var frameworkSupportFragmentInjector: DispatchingAndroidInjector<SupportFragment>

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .userModule(UserModule(this))
                .apiModule(ApiModule("http://api.openweathermap.org/", this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> = frameworkFragmentInjector

    override fun supportFragmentInjector(): DispatchingAndroidInjector<SupportFragment> = frameworkSupportFragmentInjector

}