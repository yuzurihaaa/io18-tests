package com.miandroidchallenge.ucoppp.io18test.modules

import com.miandroidchallenge.ucoppp.io18test.database.UserDatabase
import com.miandroidchallenge.ucoppp.io18test.di.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class UserModule(val application: MyApplication) {


    @Provides
    @Singleton
    fun provideUserDatabase() = UserDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideUserDao() = provideUserDatabase().userDao()
}