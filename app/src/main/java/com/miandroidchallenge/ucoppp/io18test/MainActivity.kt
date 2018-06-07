package com.miandroidchallenge.ucoppp.io18test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.miandroidchallenge.ucoppp.io18test.ui.main.util.ListenFromActivity


class MainActivity : AppCompatActivity() {

    var activityListener: ListenFromActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(this, R.id.nav_host).navigateUp()

    override fun onBackPressed() {
        if (activityListener != null) {
            if (activityListener?.doSomethingInFragment()!!) {
                activityListener?.doSomethingInFragment()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}
