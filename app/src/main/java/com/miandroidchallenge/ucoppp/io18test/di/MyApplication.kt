package com.miandroidchallenge.ucoppp.io18test.di

import android.app.Activity
import android.app.Application
import android.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var frameworkSupportFragmentInjector: DispatchingAndroidInjector<android.support.v4.app.Fragment>

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(mApplication = this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(application = this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun fragmentInjector(): AndroidInjector<Fragment> = frameworkFragmentInjector

    override fun supportFragmentInjector(): AndroidInjector<android.support.v4.app.Fragment> =
            frameworkSupportFragmentInjector

}