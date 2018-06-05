package com.miandroidchallenge.ucoppp.io18test.util

import android.app.Application
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Singleton

interface Listener<in T, in U> {
    fun onPreRequest()

    fun onResponse(`object`: T)

    fun onError(error: U)

    fun onDeviceError(error: String)

}


@Singleton
class RetrofitRequest(var application: Application) {


    fun <T, U> makeJSONRequest(request: Observable<T>, listener: Listener<T, U>?): Disposable {

        var networkError = ""

        if (!isConnected(application = application)) {
            networkError = "No internet connection"
        }

        listener?.onPreRequest()

        return request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->

                            listener?.onResponse(result)

                        },
                        { error ->
                            if(error is HttpException){

                            }

                            if (error != null) {

//                                listener?.onError(error = error as U)
                            } else {
                                listener?.onDeviceError(if (networkError.isNotEmpty()) networkError else error?.message!!)
                            }
                        }
                )
    }
}