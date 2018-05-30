package com.miandroidchallenge.ucoppp.io18test.ui.splash

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import com.miandroidchallenge.ucoppp.io18test.database.User
import com.miandroidchallenge.ucoppp.io18test.database.UserDao
import com.miandroidchallenge.ucoppp.io18test.di.MyApplication
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SplashViewModel(application: Application) : AndroidViewModel(application) {

    var testLiveData: MutableLiveData<String> = MutableLiveData()

    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {

        (application as MyApplication).appComponent.inject(this)

        sharedPreferences.edit().putString("test", "value").apply()

        testLiveData.postValue(sharedPreferences.getString("test", ""))

//        Observable.fromCallable({
//            Array(1000, { i -> i + 0 }).map {
//                userDao.insertUsers(User(
//                        name = "Name $it",
//                        age = 25
//
//                ))
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ _ ->
//                    Log.e("name", "sucess")
//                }, { error ->
//                    print(error)
//                })

    }

    @SuppressLint("CheckResult")
    fun checkUser(view: View) {
        Observable.fromCallable({
            userDao.selectAll()
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("name", it[200].name)
                }, { _ ->

                })
    }
}