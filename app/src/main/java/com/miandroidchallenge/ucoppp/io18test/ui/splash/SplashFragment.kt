package com.miandroidchallenge.ucoppp.io18test.ui.splash

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.miandroidchallenge.ucoppp.io18test.R
import com.miandroidchallenge.ucoppp.io18test.databinding.SplashFragmentBinding
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : Fragment() {

    val permissionReadLocation = 100

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
    }

    lateinit var binding: SplashFragmentBinding

    private val factory by lazy {
        SplashFactoryViewModel(
                application = activity!!.application
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener({ _ ->
            requestPermission()
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            permissionReadLocation -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    navigateToNextFragment()
                } else {
                    requestPermission()
                }
                return
            }

        // Add other 'when' lines to check for other
        // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    permissionReadLocation)
        } else {
            navigateToNextFragment()
        }
    }

    private fun navigateToNextFragment() {
        findNavController(view!!).navigate(R.id.action_splashFragment_to_mainFragment)
    }
}
