package com.miandroidchallenge.ucoppp.io18test.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
internal class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideSharedPreference() : SharedPreferences =
            provideApplication().getSharedPreferences("signIn", Context.MODE_PRIVATE)
}